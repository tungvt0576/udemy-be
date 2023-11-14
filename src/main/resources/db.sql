CREATE DATABASE `udemy_sys_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
CREATE TABLE `users` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `email` varchar(255) NOT NULL,
                         `name` varchar(255) NOT NULL,
                         `created_at` datetime NOT NULL,
                         `updated_at` datetime NOT NULL,
                         `website` varchar(255) DEFAULT NULL,
                         `avatar` varchar(255) DEFAULT NULL,
                         `description` varchar(255) DEFAULT NULL,
                         `password` varchar(255) NOT NULL,
                         `role` enum('USER','ADMIN') NOT NULL DEFAULT 'USER',
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;

CREATE TABLE `courses` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `learning_object` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                           `required_skills` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                           `course_for` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'Everyone',
                           `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                           `subtitle` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                           `course_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                           `language` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                           `level` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                           `category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                           `primarily_taught` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                           `course_image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                           `promotional_video_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                           `price` int NOT NULL,
                           `welcome_message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'Wellcome to Udemy',
                           `congratulation_message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                           `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'Already enroll!',
                           `rating` float NOT NULL,
                           `sale` int DEFAULT NULL,
                           `created_at` datetime NOT NULL,
                           `updated_at` datetime NOT NULL,
                           `user_id_cou` int NOT NULL,
                           PRIMARY KEY (`id`),
                           KEY `user_id_cou_idx` (`user_id_cou`),
                           CONSTRAINT `user_id_cou` FOREIGN KEY (`user_id_cou`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `assignments` (
                               `id` int NOT NULL AUTO_INCREMENT,
                               `type` varchar(45) NOT NULL,
                               `description` varchar(255) NOT NULL,
                               `created_at` datetime NOT NULL,
                               `updated_at` datetime NOT NULL,
                               `course_id_ass` int NOT NULL,
                               `attached_files_url` varchar(255) NOT NULL,
                               PRIMARY KEY (`id`),
                               KEY `course_id_ass_idx` (`course_id_ass`),
                               CONSTRAINT `course_id_ass` FOREIGN KEY (`course_id_ass`) REFERENCES `courses` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
CREATE TABLE `enrolls` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `type` varchar(255) NOT NULL,
                           `created_at` datetime NOT NULL,
                           `updated_at` datetime NOT NULL,
                           `course_id_enr` int NOT NULL,
                           `user_id_enr` int NOT NULL,
                           PRIMARY KEY (`id`),
                           KEY `course_id_enr_idx` (`course_id_enr`),
                           KEY `user_id_enr_idx` (`user_id_enr`),
                           CONSTRAINT `course_id_enr` FOREIGN KEY (`course_id_enr`) REFERENCES `courses` (`id`),
                           CONSTRAINT `user_id_enr` FOREIGN KEY (`user_id_enr`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
CREATE TABLE `sections` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `name` varchar(255) NOT NULL,
                            `created_at` datetime NOT NULL,
                            `updated_at` datetime NOT NULL,
                            `course_id_sec` int NOT NULL,
                            PRIMARY KEY (`id`),
                            KEY `course_id_sec_idx` (`course_id_sec`),
                            CONSTRAINT `course_id_sec` FOREIGN KEY (`course_id_sec`) REFERENCES `courses` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


CREATE TABLE `lectures` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                            `video_url` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                            `created_at` datetime NOT NULL,
                            `updated_at` datetime NOT NULL,
                            `course_id_lec` int NOT NULL,
                            `section_id_lec` int NOT NULL,
                            PRIMARY KEY (`id`),
                            KEY `course_id_lec_idx` (`course_id_lec`),
                            KEY `section_id_lec_idx` (`section_id_lec`),
                            CONSTRAINT `course_id_lec` FOREIGN KEY (`course_id_lec`) REFERENCES `courses` (`id`),
                            CONSTRAINT `section_id_lec` FOREIGN KEY (`section_id_lec`) REFERENCES `sections` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `assignments_submission` (
                                          `id` int NOT NULL,
                                          `created_at` datetime NOT NULL,
                                          `updated_at` datetime NOT NULL,
                                          `assignment_id_ass` int NOT NULL,
                                          `user_id_ass` int NOT NULL,
                                          PRIMARY KEY (`id`),
                                          KEY `assignment_id_ass_idx` (`assignment_id_ass`,`user_id_ass`),
                                          KEY `user_id_ass_idx` (`user_id_ass`),
                                          CONSTRAINT `assignment_id_ass` FOREIGN KEY (`assignment_id_ass`) REFERENCES `assignments` (`id`),
                                          CONSTRAINT `user_id_ass` FOREIGN KEY (`user_id_ass`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `disscussions` (
                                `id` int NOT NULL,
                                `comment` varchar(255) NOT NULL,
                                `disscussionscol` timestamp NOT NULL,
                                `created_at` datetime NOT NULL,
                                `updated_at` datetime NOT NULL,
                                `lecture_id_dis` int NOT NULL,
                                `user_id_dis` int NOT NULL,
                                PRIMARY KEY (`id`),
                                KEY `lecture_id_idx` (`lecture_id_dis`),
                                KEY `user_id_idx` (`user_id_dis`),
                                CONSTRAINT `lecture_id_dis` FOREIGN KEY (`lecture_id_dis`) REFERENCES `lectures` (`id`),
                                CONSTRAINT `user_id_dis` FOREIGN KEY (`user_id_dis`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `feedbacks` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `rating` int NOT NULL,
                             `feed_back` varchar(255) NOT NULL,
                             `time` timestamp NULL DEFAULT NULL,
                             `createdAt` datetime NOT NULL,
                             `updatedAt` datetime NOT NULL,
                             `course_id_fee` int NOT NULL,
                             `user_id_fee` int NOT NULL,
                             PRIMARY KEY (`id`),
                             KEY `course_id_idx` (`course_id_fee`),
                             KEY `user_id_fee_idx` (`user_id_fee`),
                             CONSTRAINT `course_id_fee` FOREIGN KEY (`course_id_fee`) REFERENCES `courses` (`id`),
                             CONSTRAINT `user_id_fee` FOREIGN KEY (`user_id_fee`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `lecture_statuses` (
                                    `id` int NOT NULL AUTO_INCREMENT,
                                    `status` tinyint(1) NOT NULL,
                                    `created_at` datetime NOT NULL,
                                    `updated_at` datetime NOT NULL,
                                    `lecture_id_lec` int NOT NULL,
                                    `user_id_lec` int NOT NULL,
                                    PRIMARY KEY (`id`),
                                    KEY `lecture_id_lec_idx` (`lecture_id_lec`),
                                    KEY `user_id_lec_idx` (`user_id_lec`),
                                    CONSTRAINT `lecture_id_lec` FOREIGN KEY (`lecture_id_lec`) REFERENCES `lectures` (`id`),
                                    CONSTRAINT `user_id_lec` FOREIGN KEY (`user_id_lec`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


ALTER TABLE `udemy_sys_db`.`users`
    CHANGE COLUMN `password` `password` VARCHAR(255) NOT NULL ;
