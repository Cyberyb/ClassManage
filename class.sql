-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: classmanage
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `cou_id` varchar(10) NOT NULL COMMENT '课程号',
  `cname` varchar(10) DEFAULT NULL COMMENT '课程名',
  `credit` int DEFAULT NULL COMMENT '学分',
  `dep_id` int DEFAULT NULL COMMENT '院系号',
  PRIMARY KEY (`cou_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES ('C01','数据库原理',5,1),('C02','操作系统',4,1),('C03','计算机网络',4,1),('C04','计算机组成原理',4,1),('C05','数据结构',4,1),('C06','通信原理',5,3),('C07','社会学',2,5);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `dep_id` int NOT NULL COMMENT '院系号',
  `dname` varchar(10) DEFAULT NULL COMMENT '院系名称',
  `adr` varchar(20) DEFAULT NULL COMMENT '地址',
  `tel` int DEFAULT NULL COMMENT '联系电话',
  PRIMARY KEY (`dep_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='院系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'计算机学院','东区计算机大楼',25125124),(2,'理学院','宝山校区理学院',NULL),(3,'通信学院','东区通信大楼',NULL),(4,'机电学院','东区机电大楼',NULL),(5,'文学院','东区文学院楼',NULL),(6,'经管学院','东区经管大楼',NULL);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `electclass`
--

DROP TABLE IF EXISTS `electclass`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `electclass` (
  `stu_id` int NOT NULL COMMENT '学号',
  `cou_id` varchar(10) NOT NULL COMMENT '课程号',
  `tea_id` varchar(10) NOT NULL COMMENT '教师号',
  `cj` int DEFAULT NULL COMMENT '成绩',
  PRIMARY KEY (`stu_id`,`cou_id`,`tea_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='选课表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `electclass`
--

LOCK TABLES `electclass` WRITE;
/*!40000 ALTER TABLE `electclass` DISABLE KEYS */;
INSERT INTO `electclass` VALUES (22001,'C01','T01',86),(22001,'C02','T02',NULL),(22001,'C03','T03',NULL),(22001,'C04','T04',NULL),(22002,'C01','T01',NULL),(22002,'C02','T02',86),(22002,'C03','T03',NULL),(22002,'C04','T04',NULL),(22004,'C02','T02',94),(22011,'C01','T02',NULL),(22014,'C01','T01',NULL),(22014,'C02','T02',90);
/*!40000 ALTER TABLE `electclass` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `openclass`
--

DROP TABLE IF EXISTS `openclass`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `openclass` (
  `cou_id` varchar(20) NOT NULL COMMENT '课程号',
  `tea_id` varchar(10) NOT NULL COMMENT '开课教师号',
  `xq` varchar(10) DEFAULT NULL COMMENT '开课学期(秋、冬、春)',
  `time` varchar(10) DEFAULT NULL COMMENT '开课时间（星期几-节数）',
  `stunum` int DEFAULT NULL COMMENT '选课人数',
  `uplim` int DEFAULT NULL COMMENT '选课人数上限',
  PRIMARY KEY (`cou_id`,`tea_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `openclass`
--

LOCK TABLES `openclass` WRITE;
/*!40000 ALTER TABLE `openclass` DISABLE KEYS */;
INSERT INTO `openclass` VALUES ('C01','T02','秋季学期','一1-2',0,50),('C02','T03','秋季学期','二1-2',0,50),('C03','T03','冬季学期\n','四3-4',0,40),('C04','T04','春季学期','一7-8',0,40),('C07','T06','秋季学期','四7-8',0,40);
/*!40000 ALTER TABLE `openclass` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `stu_id` int NOT NULL AUTO_INCREMENT COMMENT '学号',
  `name` varchar(10) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(10) DEFAULT NULL COMMENT '性别',
  `dep_id` int DEFAULT NULL COMMENT '院系号',
  `username` varchar(20) DEFAULT NULL COMMENT '账号',
  `passwd` int DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`stu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=998293508 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (22001,'小红','女',1,'xiaohong',22001),(22002,'小明','男',2,'xiaoming',22002),(22003,'小杨','男',3,'xiaoyang',22003),(22004,'小张','女',1,'xiaozhang',22004),(22005,'张三','男',1,'zhangsan',22005),(22006,'李四','男',2,'lisi',22006),(22007,'王五','男',4,'wamgwu',22007),(22008,'赵六','男',5,'zhaoliu',22008),(22009,'玖玖里','女',5,'jiujiuli',22009),(22010,'茅崎英士','男',4,'yingshi',22010),(22011,'珂朵莉','女',2,'kutori',22011),(22012,'威廉','男',3,'william',22012),(22013,'东海帝皇','女',3,'Tokaiteo',22013),(22014,'米浴','女',4,'Riceshower',22014),(22015,'麦昆','女',5,'Mcqueen',22015);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher` (
  `tea_id` varchar(10) NOT NULL COMMENT '教师号',
  `tname` varchar(10) DEFAULT NULL COMMENT '教师名',
  `dep_id` int DEFAULT NULL COMMENT '学院号',
  `username` varchar(20) DEFAULT NULL COMMENT '账户名',
  `passwd` varchar(20) DEFAULT NULL COMMENT '密码',
  `role` varchar(10) DEFAULT NULL COMMENT '角色（教师/管理员）',
  PRIMARY KEY (`tea_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES ('T01','管理员',1,'admin','123','管理员'),('T02','谢希仁',1,'xiexiren','123','教师'),('T03','汤小丹',1,'tangxiaodan','123','教师'),('T04','白中英',1,'baizhongying','123','教师'),('T05','朴灵',3,'puling','123','教师'),('T06','毛法宪',5,'maofaxian','123','教师');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-19 16:52:13
