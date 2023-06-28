CREATE DATABASE `login`;

use `login`;

CREATE TABLE `figures` (
  `idfigures` int(11) NOT NULL AUTO_INCREMENT,
  `figurename` varchar(45) NOT NULL,
  `figureimg` longblob NOT NULL,
  PRIMARY KEY (`idfigures`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `history` (
  `history_id` int(11) NOT NULL AUTO_INCREMENT,
  `playername` varchar(45) NOT NULL,
  `highscore` int(11) NOT NULL,
  `date` bigint(20) NOT NULL,
  `result` varchar(45) NOT NULL,
  PRIMARY KEY (`history_id`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `player` (
  `playerid` int(11) NOT NULL AUTO_INCREMENT,
  `playername` varchar(45) NOT NULL,
  `highscore` int(11) NOT NULL,
  PRIMARY KEY (`playerid`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
