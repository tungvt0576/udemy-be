CREATE DATABASE  IF NOT EXISTS `udemy` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `udemy`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: udemy
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `assignments`
--

DROP TABLE IF EXISTS `assignments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignments`
--

LOCK TABLES `assignments` WRITE;
/*!40000 ALTER TABLE `assignments` DISABLE KEYS */;
INSERT INTO `assignments` VALUES (5,'string','string','2023-11-25 22:40:20','2023-11-25 22:40:20',1,'string'),(6,'string','string','2023-11-25 22:40:22','2023-11-25 22:40:22',1,'string'),(7,'string','string','2023-11-25 22:40:23','2023-11-25 22:40:23',1,'string'),(8,'string','string','2023-11-25 22:40:23','2023-11-25 22:40:23',1,'string'),(9,'string','string','2023-11-25 22:40:23','2023-11-25 22:40:23',1,'string'),(10,'string','string','2023-11-25 22:40:23','2023-11-25 22:40:23',1,'string'),(11,'string','string','2023-11-25 22:40:23','2023-11-25 22:40:23',1,'string');
/*!40000 ALTER TABLE `assignments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assignments_submission`
--

DROP TABLE IF EXISTS `assignments_submission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assignments_submission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `assignment_id_ass` int NOT NULL,
  `user_id_ass` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `assignment_id_ass_idx` (`assignment_id_ass`,`user_id_ass`),
  KEY `user_id_ass_idx` (`user_id_ass`),
  CONSTRAINT `assignment_id_ass` FOREIGN KEY (`assignment_id_ass`) REFERENCES `assignments` (`id`),
  CONSTRAINT `user_id_ass` FOREIGN KEY (`user_id_ass`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignments_submission`
--

LOCK TABLES `assignments_submission` WRITE;
/*!40000 ALTER TABLE `assignments_submission` DISABLE KEYS */;
INSERT INTO `assignments_submission` VALUES (3,'2023-11-26 01:54:35','2023-11-26 01:54:35',10,5),(4,'2023-12-09 10:37:31','2023-12-09 10:37:31',5,1);
/*!40000 ALTER TABLE `assignments_submission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
  `total_enroll` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id_cou_idx` (`user_id_cou`),
  CONSTRAINT `user_id_cou` FOREIGN KEY (`user_id_cou`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES (1,'string','string','string','string','string','string','string ','string','string','string','string','string',10,'string','string','string ',5,5,'2023-11-22 21:46:32','2023-11-22 21:46:32',5,NULL),(2,'Master JavaScript','Beginner','JS developer','JS in 2 hours','JavaScript','Complete Java Script skill','from zero to hero','English','All level','IT Course','https://bom.so/mY6HBU','https://www.youtube.com/watch?v=SBmSRK3feww',100,'Wellcome','Congratulation completed this course','Already enroll',4.7,90,'2023-06-15 10:23:30','2023-06-15 10:23:30',1,NULL),(3,'Master JavaScript','Beginner','JS developer','JS in 2 hours','JavaScript','Complete Java Script skill','from zero to hero','English','All level','IT Course','https://bom.so/mY6HBU','https://www.youtube.com/watch?v=SBmSRK3feww',100,'Wellcome','Congratulation completed this course','Already enroll',4.7,90,'2023-06-15 10:23:30','2023-06-15 10:23:30',1,NULL),(4,'Master Python','Basic programing','Python Beginer','Python Botcamp','Top 1 Python course','Complete Java Python skill','from zero to hero','English','All level','IT Course','https://bom.so/2sHCna','https://www.youtube.com/watch?v=XKHEtdqhLK8',50,'Wellcome','Congratulation completed this course','Already enroll',4.7,90,'2023-06-15 10:23:30','2023-06-15 10:23:30',1,NULL),(5,'Master Python','Basic programing','Python Beginer','Python Botcamp','Top 1 Python course','Complete Java Python skill','from zero to hero','English','All level','IT Course','https://bom.so/2sHCna','https://www.youtube.com/watch?v=XKHEtdqhLK8',50,'Wellcome','Congratulation completed this course','Already enroll',4.7,90,'2023-06-15 10:23:30','2023-06-15 10:23:30',1,NULL),(7,'Master JavaScript','Beginner','JS developer','JS in 2 hours','JavaScript','Complete Java Script skill','from zero to hero','English','All level','IT Course','https://bom.so/mY6HBU','https://www.youtube.com/watch?v=SBmSRK3feww',100,'Wellcome','Congratulation completed this course','Already enroll',4.7,90,'2023-06-15 10:23:30','2023-06-15 10:23:30',1,NULL),(8,'Master Python','Basic programing','Python Beginer','Python Botcamp','Top 1 Python course','Complete Java Python skill','from zero to hero','English','All level','IT Course','https://bom.so/2sHCna','https://www.youtube.com/watch?v=XKHEtdqhLK8',50,'Wellcome','Congratulation completed this course','Already enroll',4.7,90,'2023-06-15 10:23:30','2023-06-15 10:23:30',1,NULL),(9,'string','string','string','string','string','string','string','string','string','string','string','string',0,'string','string','string',0,0,'2023-12-09 02:40:39','2023-12-09 02:40:39',1,0);
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discussions`
--

DROP TABLE IF EXISTS `discussions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discussions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `lecture_id_dis` int DEFAULT NULL,
  `user_id_dis` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkikyfo6jynhh5vknxt6m1met` (`lecture_id_dis`),
  KEY `FKg8c8sog5hhrfw9coy2loopt8u` (`user_id_dis`),
  CONSTRAINT `FKg8c8sog5hhrfw9coy2loopt8u` FOREIGN KEY (`user_id_dis`) REFERENCES `users` (`id`),
  CONSTRAINT `FKkikyfo6jynhh5vknxt6m1met` FOREIGN KEY (`lecture_id_dis`) REFERENCES `lectures` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discussions`
--

LOCK TABLES `discussions` WRITE;
/*!40000 ALTER TABLE `discussions` DISABLE KEYS */;
INSERT INTO `discussions` VALUES (3,'This course verry good for beginner learner','2023-12-08 07:59:19.414221','2023-12-08 07:59:19.414221',1,1),(5,'This course very clear','2023-12-08 07:59:19.414221','2023-12-08 07:59:19.414221',1,2),(6,'This course verry good for beginner learner','2023-12-08 07:59:19.414221','2023-12-08 07:59:19.414221',1,1),(7,'This course very clear','2023-12-08 07:59:19.414221','2023-12-08 07:59:19.414221',1,2);
/*!40000 ALTER TABLE `discussions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `disscussions`
--

DROP TABLE IF EXISTS `disscussions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disscussions`
--

LOCK TABLES `disscussions` WRITE;
/*!40000 ALTER TABLE `disscussions` DISABLE KEYS */;
/*!40000 ALTER TABLE `disscussions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enrolls`
--

DROP TABLE IF EXISTS `enrolls`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enrolls`
--

LOCK TABLES `enrolls` WRITE;
/*!40000 ALTER TABLE `enrolls` DISABLE KEYS */;
INSERT INTO `enrolls` VALUES (3,'full time','2023-12-08 07:59:19','2023-12-08 07:59:19',2,1),(4,'full time','2023-12-08 07:59:19','2023-12-08 07:59:19',2,1),(5,'full time','2023-12-08 07:59:19','2023-12-08 07:59:19',2,2);
/*!40000 ALTER TABLE `enrolls` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedbacks`
--

DROP TABLE IF EXISTS `feedbacks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedbacks` (
  `id` int NOT NULL AUTO_INCREMENT,
  `rating` int NOT NULL,
  `feed_back` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `course_id_fee` int NOT NULL,
  `user_id_fee` int NOT NULL,
  `time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `course_id_idx` (`course_id_fee`),
  KEY `user_id_fee_idx` (`user_id_fee`),
  CONSTRAINT `course_id_fee` FOREIGN KEY (`course_id_fee`) REFERENCES `courses` (`id`),
  CONSTRAINT `user_id_fee` FOREIGN KEY (`user_id_fee`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedbacks`
--

LOCK TABLES `feedbacks` WRITE;
/*!40000 ALTER TABLE `feedbacks` DISABLE KEYS */;
INSERT INTO `feedbacks` VALUES (1,5,'string','2023-12-09 11:15:42','2023-12-09 11:15:42',2,1,'2023-12-09 04:14:33'),(2,5,'string','2023-12-09 11:15:45','2023-12-09 11:15:45',2,1,'2023-12-09 04:14:33');
/*!40000 ALTER TABLE `feedbacks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lecture_statuses`
--

DROP TABLE IF EXISTS `lecture_statuses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecture_statuses`
--

LOCK TABLES `lecture_statuses` WRITE;
/*!40000 ALTER TABLE `lecture_statuses` DISABLE KEYS */;
INSERT INTO `lecture_statuses` VALUES (1,1,'2023-12-09 11:16:21','2023-12-09 11:16:21',1,1),(2,1,'2023-12-09 11:16:26','2023-12-09 11:16:26',1,2);
/*!40000 ALTER TABLE `lecture_statuses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lectures`
--

DROP TABLE IF EXISTS `lectures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lectures` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `video_url` varchar(513) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `course_id_lec` int NOT NULL,
  `section_id_lec` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `course_id_lec_idx` (`course_id_lec`),
  KEY `section_id_lec_idx` (`section_id_lec`),
  CONSTRAINT `course_id_lec` FOREIGN KEY (`course_id_lec`) REFERENCES `courses` (`id`),
  CONSTRAINT `section_id_lec` FOREIGN KEY (`section_id_lec`) REFERENCES `sections` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lectures`
--

LOCK TABLES `lectures` WRITE;
/*!40000 ALTER TABLE `lectures` DISABLE KEYS */;
INSERT INTO `lectures` VALUES (1,'string','string','2023-11-26 11:28:16','2023-11-26 11:28:16',1,1),(2,'string','string','2023-11-26 11:28:16','2023-11-26 11:28:16',1,1),(6,'Python','https://www.youtube.com/watch?v=rfscVS0vtbw&ab_channel=freeCodeCamp.org','2023-12-09 09:31:57','2023-12-09 09:31:57',1,1),(7,'Python','https://www.youtube.com/watch?v=rfscVS0vtbw&ab_channel=freeCodeCamp.org','2023-12-09 09:32:04','2023-12-09 09:32:04',2,1),(8,'Python','https://www.youtube.com/watch?v=rfscVS0vtbw&ab_channel=freeCodeCamp.org','2023-12-09 09:32:07','2023-12-09 09:32:07',3,1),(9,'Python','https://www.youtube.com/watch?v=rfscVS0vtbw&ab_channel=freeCodeCamp.org','2023-12-09 09:32:11','2023-12-09 09:32:11',4,1),(10,'Python','https://www.youtube.com/watch?v=rfscVS0vtbw&ab_channel=freeCodeCamp.org','2023-12-09 09:32:14','2023-12-09 09:32:14',5,1);
/*!40000 ALTER TABLE `lectures` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sections`
--

DROP TABLE IF EXISTS `sections`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sections` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `course_id_sec` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `course_id_sec_idx` (`course_id_sec`),
  CONSTRAINT `course_id_sec` FOREIGN KEY (`course_id_sec`) REFERENCES `courses` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sections`
--

LOCK TABLES `sections` WRITE;
/*!40000 ALTER TABLE `sections` DISABLE KEYS */;
INSERT INTO `sections` VALUES (1,'tung','2023-11-22 21:46:32','2023-11-22 21:46:32',1),(2,'Section 2','2023-12-08 13:33:22','2023-12-08 13:33:22',1),(3,'Section 1','2023-12-08 13:33:22','2023-12-08 13:33:22',1);
/*!40000 ALTER TABLE `sections` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
  `role` enum('ADMIN','USER') DEFAULT NULL,
  `money` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'string','string','2023-12-09 09:23:57','2023-12-09 09:23:57',NULL,NULL,NULL,'$2a$10$dxNueeJdSaL1jHrxLA4H..Xd4Xs5ARUuBf7vR3gj6AMusPLjq6tS6','USER',NULL),(2,'tungvt@gmail.com','tungvt','2023-12-09 09:16:24','2023-12-09 09:16:24',NULL,NULL,NULL,'$2a$10$TQZXMcaoFzXwCERtM434C.xz8p8xivvV9KhsU7yCedMZdJ04ce0Qi','USER',NULL),(5,'string2','string2','2023-11-22 21:46:32','2023-11-22 21:46:32',NULL,NULL,NULL,'$2a$10$E0upZcvqj6aKeQQ7frzAdewrb/VrCmx3wZumn6rQ4UXRivkEqL.pO','USER',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-09 11:19:32