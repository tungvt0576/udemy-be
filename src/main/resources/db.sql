CREATE DATABASE `udemy_sys_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
CREATE TABLE `assignments` (
                               `id` int NOT NULL AUTO_INCREMENT,
                               `description` varchar(255) DEFAULT NULL,
                               `type` varchar(255) DEFAULT NULL,
                               `attached_files_url` varchar(255) DEFAULT NULL,
                               `created_at` datetime(6) DEFAULT NULL,
                               `updated_at` datetime(6) DEFAULT NULL,
                               `course_id_ass` int DEFAULT NULL,
                               PRIMARY KEY (`id`),
                               KEY `FKlpv5l4b6vt8twfx9wq90x1wdb` (`course_id_ass`),
                               CONSTRAINT `FKlpv5l4b6vt8twfx9wq90x1wdb` FOREIGN KEY (`course_id_ass`) REFERENCES `courses` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `assignments_submission` (
                                          `id` int NOT NULL AUTO_INCREMENT,
                                          `created_at` datetime(6) DEFAULT NULL,
                                          `updated_at` datetime(6) DEFAULT NULL,
                                          `assignment_id_ass` int DEFAULT NULL,
                                          `user_id_ass` int DEFAULT NULL,
                                          PRIMARY KEY (`id`),
                                          KEY `FK8g1498lecjuf0iyabuwukqpym` (`assignment_id_ass`),
                                          KEY `FKakm19cdhn2n4ehrnbr7vvpft4` (`user_id_ass`),
                                          CONSTRAINT `FK8g1498lecjuf0iyabuwukqpym` FOREIGN KEY (`assignment_id_ass`) REFERENCES `assignments` (`id`),
                                          CONSTRAINT `FKakm19cdhn2n4ehrnbr7vvpft4` FOREIGN KEY (`user_id_ass`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `courses` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `category` varchar(255) NOT NULL,
                           `congratulation_message` varchar(255) NOT NULL,
                           `course_description` varchar(255) NOT NULL,
                           `course_for` varchar(255) NOT NULL,
                           `course_image_url` varchar(255) NOT NULL,
                           `created_at` datetime(6) NOT NULL,
                           `language` varchar(255) NOT NULL,
                           `learning_object` varchar(255) NOT NULL,
                           `level` varchar(255) NOT NULL,
                           `price` int NOT NULL,
                           `primarily_taught` varchar(255) DEFAULT NULL,
                           `promotional_video_url` varchar(255) DEFAULT NULL,
                           `rating` float NOT NULL,
                           `required_skills` varchar(255) NOT NULL,
                           `sale` int DEFAULT NULL,
                           `status` varchar(255) NOT NULL DEFAULT 'private',
                           `subtitle` varchar(255) DEFAULT NULL,
                           `title` varchar(255) NOT NULL,
                           `total_enroll` int NOT NULL DEFAULT '0',
                           `updated_at` datetime(6) NOT NULL,
                           `welcome_message` varchar(255) NOT NULL,
                           `user_id_cou` int NOT NULL,
                           PRIMARY KEY (`id`,`required_skills`),
                           KEY `FK7c4vr8sgbg4f8xir07hl762us` (`user_id_cou`),
                           CONSTRAINT `FK7c4vr8sgbg4f8xir07hl762us` FOREIGN KEY (`user_id_cou`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `discussions` (
                               `id` int NOT NULL AUTO_INCREMENT,
                               `comment` varchar(255) DEFAULT NULL,
                               `created_at` datetime(6) NOT NULL,
                               `updated_at` datetime(6) NOT NULL,
                               `lecture_id_dis` int NOT NULL,
                               `user_id_dis` int NOT NULL,
                               PRIMARY KEY (`id`),
                               KEY `FKg8c8sog5hhrfw9coy2loopt8u` (`user_id_dis`),
                               KEY `FKkikyfo6jynhh5vknxt6m1met` (`lecture_id_dis`),
                               CONSTRAINT `FKg8c8sog5hhrfw9coy2loopt8u` FOREIGN KEY (`user_id_dis`) REFERENCES `users` (`id`),
                               CONSTRAINT `FKkikyfo6jynhh5vknxt6m1met` FOREIGN KEY (`lecture_id_dis`) REFERENCES `lectures` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `enrolls` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `created_at` datetime(6) DEFAULT NULL,
                           `type` varchar(255) DEFAULT NULL,
                           `updated_at` datetime(6) DEFAULT NULL,
                           `course_id_enr` int NOT NULL,
                           `user_id_enr` int NOT NULL,
                           PRIMARY KEY (`id`,`user_id_enr`,`course_id_enr`),
                           KEY `FKs4cr6uwgmkrk9se6gil0u2yns` (`course_id_enr`),
                           KEY `FK7e1o05jtrmlhlf9i0msteplap` (`user_id_enr`),
                           CONSTRAINT `FK7e1o05jtrmlhlf9i0msteplap` FOREIGN KEY (`user_id_enr`) REFERENCES `users` (`id`),
                           CONSTRAINT `FKs4cr6uwgmkrk9se6gil0u2yns` FOREIGN KEY (`course_id_enr`) REFERENCES `courses` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `feedbacks` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `created_at` datetime(6) DEFAULT NULL,
                             `feed_back` varchar(255) DEFAULT NULL,
                             `rating` int DEFAULT NULL,
                             `time` datetime(6) DEFAULT NULL,
                             `updated_at` datetime(6) DEFAULT NULL,
                             `course_id_fee` int DEFAULT NULL,
                             `user_id_fee` int DEFAULT NULL,
                             PRIMARY KEY (`id`),
                             KEY `FKd371xhvh2q9hdtcspt5webuqq` (`course_id_fee`),
                             KEY `FK9h25xhrjdgo3o4rxn266lmwf5` (`user_id_fee`),
                             CONSTRAINT `FK9h25xhrjdgo3o4rxn266lmwf5` FOREIGN KEY (`user_id_fee`) REFERENCES `users` (`id`),
                             CONSTRAINT `FKd371xhvh2q9hdtcspt5webuqq` FOREIGN KEY (`course_id_fee`) REFERENCES `courses` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `lectures` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `created_at` datetime(6) DEFAULT NULL,
                            `name` varchar(255) DEFAULT NULL,
                            `updated_at` datetime(6) DEFAULT NULL,
                            `video_url` varchar(255) DEFAULT NULL,
                            `course_id_lec` int DEFAULT NULL,
                            `section_id_lec` int DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            KEY `FKogae22wn1bcdpuykjfvmtprk9` (`course_id_lec`),
                            KEY `FKm1hsbkwwae2x1ewnpp8bguj57` (`section_id_lec`),
                            CONSTRAINT `FKm1hsbkwwae2x1ewnpp8bguj57` FOREIGN KEY (`section_id_lec`) REFERENCES `sections` (`id`),
                            CONSTRAINT `FKogae22wn1bcdpuykjfvmtprk9` FOREIGN KEY (`course_id_lec`) REFERENCES `courses` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `lecture_statuses` (
                                    `id` int NOT NULL AUTO_INCREMENT,
                                    `created_at` datetime(6) DEFAULT NULL,
                                    `status` bit(1) DEFAULT NULL,
                                    `updated_at` datetime(6) DEFAULT NULL,
                                    `lecture_id_lec` int DEFAULT NULL,
                                    `user_id_lec` int DEFAULT NULL,
                                    PRIMARY KEY (`id`),
                                    KEY `FKt2944gsfp1fumgtbcl24c9m54` (`lecture_id_lec`),
                                    KEY `FKfjkqtcdswtvf9b15dhu3p3ses` (`user_id_lec`),
                                    CONSTRAINT `FKfjkqtcdswtvf9b15dhu3p3ses` FOREIGN KEY (`user_id_lec`) REFERENCES `users` (`id`),
                                    CONSTRAINT `FKt2944gsfp1fumgtbcl24c9m54` FOREIGN KEY (`lecture_id_lec`) REFERENCES `lectures` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `sections` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `created_at` datetime(6) NOT NULL,
                            `name` varchar(255) NOT NULL,
                            `updated_at` datetime(6) NOT NULL,
                            `course_id_sec` int NOT NULL,
                            PRIMARY KEY (`id`),
                            KEY `FKauo1iyary1em8mj7ru49q26ft` (`course_id_sec`),
                            CONSTRAINT `FKauo1iyary1em8mj7ru49q26ft` FOREIGN KEY (`course_id_sec`) REFERENCES `courses` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `users` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `avatar` varchar(255) DEFAULT NULL,
                         `created_at` datetime(6) NOT NULL,
                         `description` varchar(255) DEFAULT NULL,
                         `email` varchar(255) NOT NULL,
                         `money` float NOT NULL DEFAULT '0',
                         `name` varchar(255) NOT NULL,
                         `password` varchar(255) DEFAULT NULL,
                         `role` enum('ADMIN','USER') NOT NULL DEFAULT 'USER',
                         `updated_at` datetime(6) NOT NULL,
                         `website` varchar(255) DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `courses` (`learning_object`, `required_skills`, `course_for`, `title`, `subtitle`, `course_description`, `language`, `level`, `category`, `primarily_taught`, `course_image_url`, `promotional_video_url`, `price`,
                       `welcome_message`, `congratulation_message`, `status`, `rating`, `sale`, `created_at`, `updated_at`, `user_id_cou`)
VALUES
    ('Master JavaScript', 'Beginner', 'JS developer', 'JS in 2 hours', 'JavaScript', 'Complete Java Script skill', 'from zero to hero', 'English', 'All level', 'IT Course', 'https://bom.so/mY6HBU', 'https://www.youtube.com/watch?v=SBmSRK3feww', 100, 'Wellcome','Congratulation completed this course', 'Already enroll', 4.7, 90, '2023-06-15 10:23:30', '2023-06-15 10:23:30', 1);

INSERT INTO `courses` (`learning_object`, `required_skills`, `course_for`, `title`, `subtitle`, `course_description`, `language`, `level`, `category`, `primarily_taught`, `course_image_url`, `promotional_video_url`, `price`,
                       `welcome_message`, `congratulation_message`, `status`, `rating`, `sale`, `created_at`, `updated_at`, `user_id_cou`)
VALUES
    ('Master Python', 'Basic programing', 'Python Beginer', 'Python Botcamp', 'Top 1 Python course', 'Complete Java Python skill', 'from zero to hero', 'English', 'All level', 'IT Course', 'https://bom.so/2sHCna', 'https://www.youtube.com/watch?v=XKHEtdqhLK8', 50, 'Wellcome','Congratulation completed this course', 'Already enroll', 4.7, 90, '2023-06-15 10:23:30', '2023-06-15 10:23:30', 1);

# User has password that encoded so can't insert to db directly
# All request body is json file

# Step1: Register with
# { "email":"abcd@gmail.com",
#     "name":"ABC",
#     "password": "123456789"}
# Step2: Login with email+ pass by using api login
# Step3: Using API update (http://localhost:8087/api/v1/user/update/1) with body
# format here
#     {
#     "email": "minhtuyen123456789@gmail.com",
#     "name": "Truong Minh Tuyen",
#     "website": "https://www.amigoscode.com/",
#     "avatar": "https://s.net.vn/irP0",
#     "description": "very good",
#     "createdAt": null,
#     "updatedAt": "2023-12-08T07:59:19.414221",
#     "money": null,
#     "role": "USER"
# }

INSERT INTO `discussions` (`comment`, `created_at`, `updated_at`,`lecture_id_dis`,`user_id_dis`)
VALUES
    ('This course verry good for beginner learner', '2023-12-08T07:59:19.414221', '2023-12-08T07:59:19.414221',1, 1);
INSERT INTO `discussions` (`comment`, `created_at`, `updated_at`,`lecture_id_dis`,`user_id_dis`)
VALUES
    ('This course very clear', '2023-12-08T07:59:19.414221', '2023-12-08T07:59:19.414221',1, 2);

INSERT INTO `enrolls` (`created_at`, `type`, `updated_at`, `course_id_enr`, `user_id_enr`)
VALUES ('2023-12-08T07:59:19.414221', 'full time', '2023-12-08T07:59:19.414221', 2, 1);

INSERT INTO `enrolls` (`created_at`, `type`, `updated_at`, `course_id_enr`, `user_id_enr`)
VALUES ('2023-12-08T07:59:19.414221', 'full time', '2023-12-08T07:59:19.414221', 2, 2);

INSERT INTO `sections` (`created_at`, `name`, `updated_at`, `course_id_sec`)
VALUES ('2023-12-08 13:33:22.006298', 'Section 1', '2023-12-08 13:33:22.006298', 1);

INSERT INTO `sections` (`created_at`, `name`, `updated_at`, `course_id_sec`)
VALUES ('2023-12-08 13:33:22.006298', 'Section 2', '2023-12-08 13:33:22.006298', 1);
