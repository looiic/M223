-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Erstellungszeit: 17. Apr 2018 um 10:22
-- Server-Version: 10.1.13-MariaDB
-- PHP-Version: 5.6.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `jsfdb`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `language`
--

CREATE TABLE `language` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `language`
--

INSERT INTO `language` (`id`, `name`) VALUES
(1, 'Muschianisch'),
(2, 'Penisianisch'),
(3, 'Robertanisch');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `origin`
--

CREATE TABLE `origin` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `origin`
--

INSERT INTO `origin` (`id`, `name`) VALUES
(1, 'Schwiz'),
(2, 'Muschiland'),
(3, 'Robertland');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `person`
--

CREATE TABLE `person` (
  `id` int(11) NOT NULL,
  `age` int(11) NOT NULL,
  `geschlecht` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `person`
--

INSERT INTO `person` (`id`, `age`, `geschlecht`, `name`) VALUES
(1, 12, 'Unbekannt', 'Robert ränldlös');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `person_language`
--

CREATE TABLE `person_language` (
  `persons_id` int(11) NOT NULL,
  `languages_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `person_origin`
--

CREATE TABLE `person_origin` (
  `persons_id` int(11) NOT NULL,
  `origins_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `admin` bit(1) NOT NULL,
  `user` bit(1) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_pass` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `user`
--

INSERT INTO `user` (`id`, `admin`, `user`, `user_name`, `user_pass`) VALUES
(1, b'1', b'1', 'Robert', 'test');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user_role`
--

CREATE TABLE `user_role` (
  `user_name` varchar(15) NOT NULL,
  `role_name` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `user_role`
--

INSERT INTO `user_role` (`user_name`, `role_name`) VALUES
('Robert', 'admin'),
('Robert', 'user');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `language`
--
ALTER TABLE `language`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `origin`
--
ALTER TABLE `origin`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `person_language`
--
ALTER TABLE `person_language`
  ADD KEY `FK4pmo3yui3oebd76s3o04is9ee` (`languages_id`),
  ADD KEY `FKcs6g0yc41cvlihbgctid744g9` (`persons_id`);

--
-- Indizes für die Tabelle `person_origin`
--
ALTER TABLE `person_origin`
  ADD KEY `FKh82uvto1jgprrjljxrv7gnc1v` (`origins_id`),
  ADD KEY `FKjm28wwff4fnd4hp3tfgp51xf3` (`persons_id`);

--
-- Indizes für die Tabelle `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`user_name`,`role_name`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `language`
--
ALTER TABLE `language`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT für Tabelle `origin`
--
ALTER TABLE `origin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT für Tabelle `person`
--
ALTER TABLE `person`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT für Tabelle `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `person_language`
--
ALTER TABLE `person_language`
  ADD CONSTRAINT `FK4pmo3yui3oebd76s3o04is9ee` FOREIGN KEY (`languages_id`) REFERENCES `language` (`id`),
  ADD CONSTRAINT `FKcs6g0yc41cvlihbgctid744g9` FOREIGN KEY (`persons_id`) REFERENCES `person` (`id`);

--
-- Constraints der Tabelle `person_origin`
--
ALTER TABLE `person_origin`
  ADD CONSTRAINT `FKh82uvto1jgprrjljxrv7gnc1v` FOREIGN KEY (`origins_id`) REFERENCES `origin` (`id`),
  ADD CONSTRAINT `FKjm28wwff4fnd4hp3tfgp51xf3` FOREIGN KEY (`persons_id`) REFERENCES `person` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
