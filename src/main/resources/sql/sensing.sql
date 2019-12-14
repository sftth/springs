-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.2.7-MariaDB-log - Source distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- sensing 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `sensing` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `sensing`;

-- 테이블 sensing.newtech 구조 내보내기
CREATE TABLE IF NOT EXISTS `newtech` (
  `content` varchar(50) DEFAULT NULL,
  `keyword` varchar(200) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `definition` varchar(1000) DEFAULT NULL,
  `history` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.
-- 테이블 sensing.users 구조 내보내기
CREATE TABLE IF NOT EXISTS `users` (
  `email` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '???',
  `password` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `name` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `grade` varchar(50) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='사용자 정보';

-- 내보낼 데이터가 선택되어 있지 않습니다.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
