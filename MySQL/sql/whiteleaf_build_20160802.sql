CREATE DATABASE  IF NOT EXISTS `whiteleaf` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `whiteleaf`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: whiteleaf
-- ------------------------------------------------------
-- Server version	5.7.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `authors`
--

DROP TABLE IF EXISTS `authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authors` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `NAME_UNIQUE` (`NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authors`
--

LOCK TABLES `authors` WRITE;
/*!40000 ALTER TABLE `authors` DISABLE KEYS */;
INSERT INTO `authors` VALUES (1,'Dante Alighieri'),(6,'Herman Parish'),(2,'John W. Campbell'),(3,'Niccolò Macchiavelli'),(4,'Roger Priddy'),(5,'Various Authors');
/*!40000 ALTER TABLE `authors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `books` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `TITLE` mediumtext NOT NULL,
  `AUTHOR_ID` int(11) NOT NULL,
  `ISBN` varchar(13) NOT NULL,
  `PUBLICATION_DATE` date NOT NULL,
  `PUBLISHER_ID` int(11) NOT NULL,
  `PAGE_COUNT` int(11) NOT NULL,
  `SUMMARY` mediumtext NOT NULL,
  `ILLUSTRATION` varchar(255) DEFAULT NULL,
  `CATEGORY_ID` int(11) NOT NULL,
  `PRICE` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `ISBN_UNIQUE` (`ISBN`),
  KEY `CATEGORY_idx` (`CATEGORY_ID`),
  KEY `AUTHOR_idx` (`AUTHOR_ID`),
  KEY `PUBLISHER_idx` (`PUBLISHER_ID`),
  CONSTRAINT `AUTHOR` FOREIGN KEY (`AUTHOR_ID`) REFERENCES `authors` (`id`),
  CONSTRAINT `CATEGORY` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `categories` (`id`),
  CONSTRAINT `PUBLISHER` FOREIGN KEY (`PUBLISHER_ID`) REFERENCES `publishers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'The Divine Comedy: Volume 1: Inferno',1,'9780142437223','2002-12-31',3,432,'This vigorous translation of Inferno preserves Dante\'s simple, natural style, and captures the swift movement of the original Italian verse. Mark Musa\'s blank verse rendition of the poet\'s journey through the circles of hell recreates for the modern reader the rich meanings that Dante\'s poem had for his contemporaries. Musa\'s introduction and commentaries on each of the cantos brilliantly illuminate the text. ','divine_comedy_inferno.jpg',3,18.00),(2,'Who Goes There?: The Novella That Formed the Basis of the Thing',2,'9780982332207','2009-04-01',4,168,'\"Who Goes There?\": The novella that formed the basis of \"The Thing\" is the John W. Campbell classic about an antarctic research camp that discovers and thaws the ancient, frozen body of a crash-landed alien. The creature revives with terrifying results, shape-shifting to assume the exact form of animal and man, alike. Paranoia ensues as a band of frightened men work to discern friend from foe, and destroy the menace before it challenges all of humanity!','who_goes_there.jpg',4,15.95),(3,'The Divine Comedy, Vol. II: Purgatory',1,'9780140444421','2002-12-31',3,399,'Beginning with Dante\'s liberation from Hell, Purgatory relates his ascent, accompanied by Virgil, of the Mount of Purgatory - a mountain of nine levels, formed from rock forced upwards when God threw Satan into depths of the earth. As he travels through the first seven levels, Dante observes the sinners who are waiting for their release into Paradise, and through these encounters he is himself transformed into a stronger and better man. For it is only when he has learned from each of these levels that he can ascend to the gateway to Heaven: the Garden of Eden. The second part of one of the greatest epic poems, Purgatory is an enthralling Christian allegory of sin, redemption and ultimate enlightenment.','divine_comedy_purgatory.jpg',3,18.00),(4,'The Prince',3,'9780486272740','1992-09-21',2,80,'As a young Florentine envoy to the courts of France and the Italian principalities, Niccolò Machiavelli (1469-1527) was able to observe firsthand the lives of people strongly united under one powerful ruler. His fascination with that political rarity and his intense desire to see the Medici family assume a similar role in Italy provided the foundation for his \"primer for princes.\" In this classic guide to acquiring and maintaining political power, Machiavelli used a rational approach to advise prospective rulers, developing logical arguments and alternatives for a number of potential problems, among them governing hereditary monarchies, dealing with colonies and the treatment of conquered peoples. Refreshing in its directness, yet often disturbing in its cold practicality, The Prince sets down a frighteningly pragmatic formula for political fortune. Starkly relevant to the political upheavals of the 20th century, this calculating prescription for power remains today, nearly 500 years after it was written, a timely and startling lesson in the practice of autocratic rule that continues to be much read and studied by students, scholars and general readers as well.','the_prince.jpg',7,2.50),(5,'The Divine Comedy, Vol. 3: Paradise',1,'9780140444438','2002-12-31',3,464,'In his translation of Paradise, Mark Musa exhibits the same sensitivity to language and knowledge of translation that enabled his versions of Inferno and Purgatory to capture the vibrant power and full dramatic force of Dante’s poetry. Dante relates his mystical interpretation of the heavens, and his moment of transcendent glory, as he journeys, first with Beatrice, then alone, toward the Trinity. Professor Musa’s extraordinary translation and his interpretive commentary, informative glossary, and bibliography clarify the theological themes and make Dante accessible to the English-speaking public.','divine_comedy_paradise.jpg',3,18.00),(6,'My Big Animal Book',4,'9780312511074','2011-05-10',4,12,'This is the perfect book for kids who love animals. On the big, sturdy board pages, they\'ll discover bright, bold photographs of all different kinds of animals, from pets to farm animals to birds. Each has their name written underneath, so that children can learn what they\'re called, build their animal vocabulary, and start to develop word and picture association.','my_big_animal_book.jpg',2,5.95),(7,'101 Great American Poems',5,'9780486401584','1998-01-21',2,96,'Focusing on popular verse from the nineteenth and twentieth centuries, this treasury of great American poems offers a taste of the nation\'s rich poetic legacy. Selected for both popularity and literary quality, the compilation includes Robert Frost\'s \"Stopping by the Woods on a Snowy Evening,\" Walt Whitman\'s \"I Hear America Singing,\" and Ralph Waldo Emerson\'s \"Concord Hymn,\" as well as poems by Langston Hughes, Emily Dickinson, T. S. Eliot, Marianne Moore, and many other notables.','101_great_american_poems.jpg',1,2.70),(8,'Louvre: All the Paintings',5,'9781579128869','2011-11-15',4,784,'An historic publishing event! Endorsed by the Louvre and for the first time ever, every painting from the world\'s most popular museum is available in one stunning book. All 3,022 paintings on display in the permanent painting collection of the Louvre are presented in full color in this striking, slipcased book. ','the_louvre.jpg',5,43.50),(9,'Amelia Bedelia Chapter Book Box Set: Books 1-4',6,'9780062334206','2014-09-04',4,160,'Amelia Bedelia is all boxed up—literally! This boxed set includes the first four books in the bestselling Amelia Bedelia Chapter Book series: Amelia Bedelia Means Business, Amelia Bedelia Unleashed, Amelia Bedelia Road Trip! and Amelia Bedelia Goes Wild! The Amelia Bedelia books have sold more than 35 million copies.','amelia_bedelia_chapter_book_box_set.jpg',6,19.96);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `CATEGORY` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `CATEGORY_UNIQUE` (`CATEGORY`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Arts'),(6,'Chapter Books'),(2,'Educational'),(3,'Literature'),(7,'Non-fiction'),(5,'Picture Books'),(4,'Science Fiction and Fantasy');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credit_card_providers`
--

DROP TABLE IF EXISTS `credit_card_providers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `credit_card_providers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` mediumtext NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `NAME_UNIQUE` (`NAME`(255))
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credit_card_providers`
--

LOCK TABLES `credit_card_providers` WRITE;
/*!40000 ALTER TABLE `credit_card_providers` DISABLE KEYS */;
INSERT INTO `credit_card_providers` VALUES (1,'Visa'),(2,'Mastercard'),(3,'Discover'),(4,'American Express');
/*!40000 ALTER TABLE `credit_card_providers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) NOT NULL,
  `CREDIT_CARD_ID` int(11) NOT NULL,
  `SHIPPING_ADDRESS_ID` int(11) NOT NULL,
  `BILLING_ADDRESS_ID` int(11) NOT NULL,
  `ORDERED_BOOKS` mediumtext NOT NULL,
  `TOTAL_COST` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `USER_ID_idx` (`USER_ID`),
  KEY `CREDIT_CARD_ID_idx` (`CREDIT_CARD_ID`),
  KEY `SHIPPING_ADDRESS_ID_idx` (`SHIPPING_ADDRESS_ID`),
  KEY `BILLING_ADDRESS_ID_idx` (`BILLING_ADDRESS_ID`),
  CONSTRAINT `ORDERS_BILLING_ADDRESS_ID` FOREIGN KEY (`BILLING_ADDRESS_ID`) REFERENCES `user_billing_address` (`id`),
  CONSTRAINT `ORDERS_CREDIT_CARD_ID` FOREIGN KEY (`CREDIT_CARD_ID`) REFERENCES `user_credit_cards` (`id`),
  CONSTRAINT `ORDERS_SHIPPING_ADDRESS_ID` FOREIGN KEY (`SHIPPING_ADDRESS_ID`) REFERENCES `user_shipping_address` (`id`),
  CONSTRAINT `ORDERS_USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `user_names` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publishers`
--

DROP TABLE IF EXISTS `publishers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `publishers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` text NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `NAME_UNIQUE` (`NAME`(255))
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publishers`
--

LOCK TABLES `publishers` WRITE;
/*!40000 ALTER TABLE `publishers` DISABLE KEYS */;
INSERT INTO `publishers` VALUES (1,'Hackett'),(2,'Dover'),(3,'Penguin'),(4,'Random House'),(5,'Oxford');
/*!40000 ALTER TABLE `publishers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_billing_address`
--

DROP TABLE IF EXISTS `user_billing_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_billing_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) NOT NULL,
  `ADDRESS` mediumtext NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `USER_ID_idx` (`USER_ID`),
  CONSTRAINT `USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `user_names` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_billing_address`
--

LOCK TABLES `user_billing_address` WRITE;
/*!40000 ALTER TABLE `user_billing_address` DISABLE KEYS */;
INSERT INTO `user_billing_address` VALUES (1,2,'123 Onetwothree St., Baltimore, MD. 21117');
/*!40000 ALTER TABLE `user_billing_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_book_wishlist`
--

DROP TABLE IF EXISTS `user_book_wishlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_book_wishlist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) NOT NULL,
  `BOOK_ID` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `USER_ID_idx` (`USER_ID`),
  KEY `BOOK_ID_idx` (`BOOK_ID`),
  CONSTRAINT `WISHLIST_BOOK_ID` FOREIGN KEY (`BOOK_ID`) REFERENCES `books` (`id`),
  CONSTRAINT `WISHLIST_USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `user_names` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_book_wishlist`
--

LOCK TABLES `user_book_wishlist` WRITE;
/*!40000 ALTER TABLE `user_book_wishlist` DISABLE KEYS */;
INSERT INTO `user_book_wishlist` VALUES (1,2,8),(2,2,9);
/*!40000 ALTER TABLE `user_book_wishlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_credit_cards`
--

DROP TABLE IF EXISTS `user_credit_cards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_credit_cards` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) NOT NULL,
  `CARD_PROVIDER_ID` int(11) NOT NULL,
  `CARD_NUMBER` varchar(45) NOT NULL,
  `EXPIRATION_DATE` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `USER_NAME_idx` (`USER_ID`),
  KEY `BANK_NAME_idx` (`CARD_PROVIDER_ID`),
  CONSTRAINT `CARD_PROVIDER_NAME` FOREIGN KEY (`CARD_PROVIDER_ID`) REFERENCES `credit_card_providers` (`id`),
  CONSTRAINT `USER_NAME` FOREIGN KEY (`USER_ID`) REFERENCES `user_names` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_credit_cards`
--

LOCK TABLES `user_credit_cards` WRITE;
/*!40000 ALTER TABLE `user_credit_cards` DISABLE KEYS */;
INSERT INTO `user_credit_cards` VALUES (1,2,2,'1234123412341234','2016-12-31');
/*!40000 ALTER TABLE `user_credit_cards` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_email_address`
--

DROP TABLE IF EXISTS `user_email_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_email_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) NOT NULL,
  `ADDRESS` mediumtext NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `USER_ID_idx` (`USER_ID`),
  CONSTRAINT `EMAIL_ADDRESS_USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `user_names` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_email_address`
--

LOCK TABLES `user_email_address` WRITE;
/*!40000 ALTER TABLE `user_email_address` DISABLE KEYS */;
INSERT INTO `user_email_address` VALUES (1,1,'ADMIN@localhost'),(2,2,'ikilbou1@jhu.edu');
/*!40000 ALTER TABLE `user_email_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_names`
--

DROP TABLE IF EXISTS `user_names`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_names` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` mediumtext,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_names`
--

LOCK TABLES `user_names` WRITE;
/*!40000 ALTER TABLE `user_names` DISABLE KEYS */;
INSERT INTO `user_names` VALUES (1,'ADMIN'),(2,'ikilbou1');
/*!40000 ALTER TABLE `user_names` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_passwords`
--

DROP TABLE IF EXISTS `user_passwords`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_passwords` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) NOT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `USER_ID_idx` (`USER_ID`),
  CONSTRAINT `PASSWORDS_USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `user_names` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_passwords`
--

LOCK TABLES `user_passwords` WRITE;
/*!40000 ALTER TABLE `user_passwords` DISABLE KEYS */;
INSERT INTO `user_passwords` VALUES (1,1,'PASSWORD'),(2,2,'wordpass!');
/*!40000 ALTER TABLE `user_passwords` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_shipping_address`
--

DROP TABLE IF EXISTS `user_shipping_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_shipping_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) NOT NULL,
  `SHIPPING_ADDRESS` mediumtext NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `USER_ID_UNIQUE` (`USER_ID`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  CONSTRAINT `USER` FOREIGN KEY (`USER_ID`) REFERENCES `user_names` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_shipping_address`
--

LOCK TABLES `user_shipping_address` WRITE;
/*!40000 ALTER TABLE `user_shipping_address` DISABLE KEYS */;
INSERT INTO `user_shipping_address` VALUES (1,2,'123 Onetwothree St., Baltimore, MD. 21117');
/*!40000 ALTER TABLE `user_shipping_address` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-08-02 22:04:53
