pipeline {
    agent any
    tools{
        maven 'maven'
        jdk 'jdk-17'
    }
    environment{
        REPO_GITHUB_URL = 'https://github.com/tungvt0576/udemy-be.git'
        REPO_BRANCH = 'dev'
        CREDENTIAL = 'udemy_be'
        IMAGE = 'udemy-be'
        IMAGE_PUB = 'tungvt200576/udemy-be'
        PORT = 8087
        SSH_HOST = '192.168.0.225'
        SSH_USER = 'dev'
        URL_DATABASE='jdbc:mysql://192.168.0.80:3306/udemy'
        DB_USERNAME='root'
        DB_PASSWORD='root'
    }
    stages {
        stage('Check out') {
            steps {
               checkout scmGit(
                   branches: [[name: REPO_BRANCH ]],
                   extensions: [],
                   userRemoteConfigs: [[credentialsId: CREDENTIAL , url: REPO_GITHUB_URL ]]
               )
            }
        }
        stage('Maven Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Build and Push image') {
            steps {
               script {
                 sh "docker build --rm -t ${IMAGE} ."
                 sh "docker tag ${IMAGE} ${IMAGE_PUB}"
                 sh "docker rmi -f ${IMAGE}"
                 sh "docker push ${IMAGE_PUB}"
               }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    sh """
                        ssh ${SSH_USER}@${SSH_HOST} '
                            docker rm -f ${IMAGE} &&
                            docker rmi -f ${IMAGE_PUB}
                        '
                    """
                    sh """
                        ssh ${SSH_USER}@${SSH_HOST} '
                            docker run -d -p ${PORT}:${PORT} \
                            --network udemy --restart always \
                            -e URL_DATABASE=${URL_DATABASE} \
                            -e DB_USERNAME=${DB_USERNAME} \
                            -e DB_PASSWORD=${DB_PASSWORD} \
                            --name ${IMAGE} ${IMAGE_PUB}
                        '
                    """
                }
            }
        }


    }
}