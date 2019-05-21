-- MySQL Workbench Synchronization
-- Generated: 2019-05-11 14:50
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Jatniel Martínez

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- Se crea la base de datos

CREATE SCHEMA IF NOT EXISTS `tutocitas` DEFAULT CHARACTER SET utf8 ;

-- Se crean las tablas

CREATE TABLE IF NOT EXISTS `tutocitas`.`Usuario` (
  `idUsuario` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(40) NOT NULL,
  `contrasena` VARCHAR(15) NOT NULL,
  `tipoUsuario` VARCHAR(13) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `apPaterno` VARCHAR(20) NOT NULL,
  `apMaterno` VARCHAR(20) NULL DEFAULT NULL,
  `correo` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`idUsuario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `tutocitas`.`Administrador` (
  `idAdministrador` INT(11) NOT NULL AUTO_INCREMENT,
  `Usuario_idUsuario` INT(11) NOT NULL,
  PRIMARY KEY (`idAdministrador`),
  INDEX `fk_Administrador_Usuario_idx` (`Usuario_idUsuario` ASC),
  CONSTRAINT `fk_Administrador_Usuario`
    FOREIGN KEY (`Usuario_idUsuario`)
    REFERENCES `tutocitas`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `tutocitas`.`Tutor` (
  `idTutor` INT(11) NOT NULL AUTO_INCREMENT,
  `noPersonal` VARCHAR(5) NOT NULL,
  `Usuario_idUsuario` INT(11) NOT NULL,
  PRIMARY KEY (`idTutor`),
  INDEX `fk_tutor_Usuario1_idx` (`Usuario_idUsuario` ASC),
  CONSTRAINT `fk_tutor_Usuario1`
    FOREIGN KEY (`Usuario_idUsuario`)
    REFERENCES `tutocitas`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `tutocitas`.`Tutorado` (
  `idTutorado` INT(11) NOT NULL AUTO_INCREMENT,
  `carrera` VARCHAR(40) NOT NULL,
  `matricula` VARCHAR(9) NOT NULL,
  `Usuario_idUsuario` INT(11) NOT NULL,
  `tutor_idTutor` INT(11) NOT NULL,
  PRIMARY KEY (`idTutorado`),
  INDEX `fk_Tutorado_Usuario1_idx` (`Usuario_idUsuario` ASC),
  INDEX `fk_Tutorado_tutor1_idx` (`tutor_idTutor` ASC),
  CONSTRAINT `fk_Tutorado_Usuario1`
    FOREIGN KEY (`Usuario_idUsuario`)
    REFERENCES `tutocitas`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Tutorado_tutor1`
    FOREIGN KEY (`tutor_idTutor`)
    REFERENCES `tutocitas`.`Tutor` (`idTutor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `tutocitas`.`Tutoria` (
  `idTutoria` INT(11) NOT NULL AUTO_INCREMENT,
  `fecha` DATE NOT NULL,
  `hora` DATETIME NOT NULL,
  `cancelada` TINYINT(1) NOT NULL,
  `causa` VARCHAR(100) NULL DEFAULT NULL,
  `Tutorado_idTutorado` INT(11) NOT NULL,
  `tutor_idTutor` INT(11) NOT NULL,
  PRIMARY KEY (`idTutoria`),
  INDEX `fk_Tutoria_Tutorado1_idx` (`Tutorado_idTutorado` ASC),
  INDEX `fk_Tutoria_tutor1_idx` (`tutor_idTutor` ASC),
  CONSTRAINT `fk_Tutoria_Tutorado1`
    FOREIGN KEY (`Tutorado_idTutorado`)
    REFERENCES `tutocitas`.`Tutorado` (`idTutorado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Tutoria_tutor1`
    FOREIGN KEY (`tutor_idTutor`)
    REFERENCES `tutocitas`.`Tutor` (`idTutor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `tutocitas`.`Reporte` (
  `idReporte` INT(11) NOT NULL AUTO_INCREMENT,
  `numTutoria` INT(11) NOT NULL,
  `duracion` INT(11) NOT NULL,
  `atendida` TINYINT(1) NOT NULL,
  `causa` VARCHAR(100) NULL DEFAULT NULL,
  `Tutoria_idTutoria` INT(11) NOT NULL,
  PRIMARY KEY (`idReporte`),
  INDEX `fk_Reporte_Tutoria1_idx` (`Tutoria_idTutoria` ASC),
  CONSTRAINT `fk_Reporte_Tutoria1`
    FOREIGN KEY (`Tutoria_idTutoria`)
    REFERENCES `tutocitas`.`Tutoria` (`idTutoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `tutocitas`.`Periodo` (
  `idPeriodo` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`idPeriodo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `tutocitas`.`Bloque` (
  `idBloque` INT(11) NOT NULL AUTO_INCREMENT,
  `horaInicio` VARCHAR(5) NOT NULL,
  `horaFin` VARCHAR(5) NOT NULL,
  `dia` VARCHAR(9) NOT NULL,
  PRIMARY KEY (`idBloque`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `tutocitas`.`Tutor_has_bloque` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `Tutor_idTutor` INT(11) NOT NULL,
  `Periodo_idPeriodo` INT(11) NOT NULL,
  `Bloque_idBloque` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Tutor_has_bloque_Tutor1_idx` (`Tutor_idTutor` ASC),
  INDEX `fk_Tutor_has_bloque_Periodo1_idx` (`Periodo_idPeriodo` ASC),
  INDEX `fk_Tutor_has_bloque_Bloque1_idx` (`Bloque_idBloque` ASC),
  CONSTRAINT `fk_Tutor_has_bloque_Tutor1`
    FOREIGN KEY (`Tutor_idTutor`)
    REFERENCES `tutocitas`.`Tutor` (`idTutor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Tutor_has_bloque_Periodo1`
    FOREIGN KEY (`Periodo_idPeriodo`)
    REFERENCES `tutocitas`.`Periodo` (`idPeriodo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Tutor_has_bloque_Bloque1`
    FOREIGN KEY (`Bloque_idBloque`)
    REFERENCES `tutocitas`.`Bloque` (`idBloque`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- Agregando datos a Periodo

START TRANSACTION;
USE `tutocitas`;
INSERT INTO `tutocitas`.`Periodo` (`nombre`) VALUES ('AGO2014-ENE2015');
INSERT INTO `tutocitas`.`Periodo` (`nombre`) VALUES ('FEB-JUL2015');
INSERT INTO `tutocitas`.`Periodo` (`nombre`) VALUES ('AGO2015-ENE2016');
INSERT INTO `tutocitas`.`Periodo` (`nombre`) VALUES ('FEB-JUL2016');
INSERT INTO `tutocitas`.`Periodo` (`nombre`) VALUES ('AGO2016-ENE2017');
INSERT INTO `tutocitas`.`Periodo` (`nombre`) VALUES ('FEB-JUL2017');
INSERT INTO `tutocitas`.`Periodo` (`nombre`) VALUES ('AGO2017-ENE2018');
INSERT INTO `tutocitas`.`Periodo` (`nombre`) VALUES ('FEB-JUL2018');
INSERT INTO `tutocitas`.`Periodo` (`nombre`) VALUES ('AGO2018-ENE2019');
INSERT INTO `tutocitas`.`Periodo` (`nombre`) VALUES ('FEB-JUL2019');
COMMIT;

-- Agregando datos a Bloque

START TRANSACTION,
USE `tutocitas`;
-- Lunes
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('07:00', '07:30', 'Lunes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('07:30', '08:00', 'Lunes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('08:00', '08:30', 'Lunes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('08:30', '09:00', 'Lunes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('09:00', '09:30', 'Lunes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('09:30', '10:00', 'Lunes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('10:00', '10:30', 'Lunes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('10:30', '11:00', 'Lunes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('11:00', '11:30', 'Lunes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('11:30', '12:00', 'Lunes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('12:00', '12:30', 'Lunes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('12:30', '13:00', 'Lunes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('13:00', '13:30', 'Lunes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('13:30', '14:00', 'Lunes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('14:00', '14:30', 'Lunes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('14:30', '15:00', 'Lunes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('15:00', '15:30', 'Lunes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('15:30', '16:00', 'Lunes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('16:00', '16:30', 'Lunes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('16:30', '17:00', 'Lunes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('17:00', '17:30', 'Lunes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('17:30', '18:00', 'Lunes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('18:00', '18:30', 'Lunes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('18:30', '19:00', 'Lunes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('19:00', '19:30', 'Lunes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('19:30', '20:00', 'Lunes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('20:00', '20:30', 'Lunes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('20:30', '21:00', 'Lunes');
-- Martes
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('07:00', '07:30', 'Martes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('07:30', '08:00', 'Martes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('08:00', '08:30', 'Martes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('08:30', '09:00', 'Martes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('09:00', '09:30', 'Martes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('09:30', '10:00', 'Martes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('10:00', '10:30', 'Martes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('10:30', '11:00', 'Martes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('11:00', '11:30', 'Martes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('11:30', '12:00', 'Martes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('12:00', '12:30', 'Martes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('12:30', '13:00', 'Martes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('13:00', '13:30', 'Martes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('13:30', '14:00', 'Martes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('14:00', '14:30', 'Martes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('14:30', '15:00', 'Martes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('15:00', '15:30', 'Martes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('15:30', '16:00', 'Martes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('16:00', '16:30', 'Martes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('16:30', '17:00', 'Martes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('17:00', '17:30', 'Martes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('17:30', '18:00', 'Martes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('18:00', '18:30', 'Martes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('18:30', '19:00', 'Martes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('19:00', '19:30', 'Martes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('19:30', '20:00', 'Martes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('20:00', '20:30', 'Martes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('20:30', '21:00', 'Martes');
-- Miércoles
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('07:00', '07:30', 'Miércoles');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('07:30', '08:00', 'Miércoles');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('08:00', '08:30', 'Miércoles');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('08:30', '09:00', 'Miércoles');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('09:00', '09:30', 'Miércoles');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('09:30', '10:00', 'Miércoles');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('10:00', '10:30', 'Miércoles');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('10:30', '11:00', 'Miércoles');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('11:00', '11:30', 'Miércoles');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('11:30', '12:00', 'Miércoles');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('12:00', '12:30', 'Miércoles');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('12:30', '13:00', 'Miércoles');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('13:00', '13:30', 'Miércoles');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('13:30', '14:00', 'Miércoles');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('14:00', '14:30', 'Miércoles');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('14:30', '15:00', 'Miércoles');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('15:00', '15:30', 'Miércoles');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('15:30', '16:00', 'Miércoles');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('16:00', '16:30', 'Miércoles');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('16:30', '17:00', 'Miércoles');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('17:00', '17:30', 'Miércoles');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('17:30', '18:00', 'Miércoles');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('18:00', '18:30', 'Miércoles');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('18:30', '19:00', 'Miércoles');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('19:00', '19:30', 'Miércoles');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('19:30', '20:00', 'Miércoles');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('20:00', '20:30', 'Miércoles');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('20:30', '21:00', 'Miércoles');
-- Jueves
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('07:00', '07:30', 'Jueves');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('07:30', '08:00', 'Jueves');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('08:00', '08:30', 'Jueves');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('08:30', '09:00', 'Jueves');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('09:00', '09:30', 'Jueves');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('09:30', '10:00', 'Jueves');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('10:00', '10:30', 'Jueves');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('10:30', '11:00', 'Jueves');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('11:00', '11:30', 'Jueves');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('11:30', '12:00', 'Jueves');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('12:00', '12:30', 'Jueves');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('12:30', '13:00', 'Jueves');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('13:00', '13:30', 'Jueves');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('13:30', '14:00', 'Jueves');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('14:00', '14:30', 'Jueves');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('14:30', '15:00', 'Jueves');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('15:00', '15:30', 'Jueves');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('15:30', '16:00', 'Jueves');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('16:00', '16:30', 'Jueves');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('16:30', '17:00', 'Jueves');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('17:00', '17:30', 'Jueves');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('17:30', '18:00', 'Jueves');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('18:00', '18:30', 'Jueves');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('18:30', '19:00', 'Jueves');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('19:00', '19:30', 'Jueves');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('19:30', '20:00', 'Jueves');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('20:00', '20:30', 'Jueves');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('20:30', '21:00', 'Jueves');
-- Viernes
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('07:00', '07:30', 'Viernes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('07:30', '08:00', 'Viernes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('08:00', '08:30', 'Viernes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('08:30', '09:00', 'Viernes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('09:00', '09:30', 'Viernes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('09:30', '10:00', 'Viernes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('10:00', '10:30', 'Viernes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('10:30', '11:00', 'Viernes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('11:00', '11:30', 'Viernes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('11:30', '12:00', 'Viernes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('12:00', '12:30', 'Viernes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('12:30', '13:00', 'Viernes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('13:00', '13:30', 'Viernes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('13:30', '14:00', 'Viernes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('14:00', '14:30', 'Viernes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('14:30', '15:00', 'Viernes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('15:00', '15:30', 'Viernes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('15:30', '16:00', 'Viernes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('16:00', '16:30', 'Viernes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('16:30', '17:00', 'Viernes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('17:00', '17:30', 'Viernes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('17:30', '18:00', 'Viernes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('18:00', '18:30', 'Viernes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('18:30', '19:00', 'Viernes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('19:00', '19:30', 'Viernes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('19:30', '20:00', 'Viernes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('20:00', '20:30', 'Viernes');
INSERT INTO `tutocitas`.`bloque` (`horaInicio`, `horaFin`, `dia`) VALUES ('20:30', '21:00', 'Viernes');
COMMIT;

-- Agregar un administrador

START TRANSACTION;
USE `tutocitas`;
INSERT INTO `tutocitas`.`Usuario` (`username`, `contrasena`, `tipoUsuario`, `nombre`, `apPaterno`, `correo`) VALUES ('admin', 'abc123', 'Administrador', 'Administrador', 'Anónimo', 'alguien@example.com');
INSERT INTO `tutocitas`.`Administrador` (`Usuario_idUsuario`) VALUES (1);
COMMIT;
