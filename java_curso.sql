CREATE SCHEMA `java_curso` ;

CREATE TABLE `java_curso`.`productos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `precio` INT NULL,
  `fecha_registro` DATETIME NULL,
  PRIMARY KEY (`id`));

INSERT INTO `java_curso`.`productos` (`nombre`, `precio`, `fecha_registro`) VALUES ('Iphone 5', '15000', '2022-03-12');

CREATE TABLE `java_curso`.`categorias` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

ALTER TABLE `java_curso`.`productos` 
ADD COLUMN `categoria_id` INT NULL AFTER `fecha_registro`;

ALTER TABLE `java_curso`.`productos` 
ADD INDEX `productos_categorias_idx` (`categoria_id` ASC) VISIBLE;
;
ALTER TABLE `java_curso`.`productos` 
ADD CONSTRAINT `productos_categorias`
  FOREIGN KEY (`categoria_id`)
  REFERENCES `java_curso`.`categorias` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

INSERT INTO `java_curso`.`categorias` (`nombre`) VALUES ('Deportes');
INSERT INTO `java_curso`.`categorias` (`nombre`) VALUES ('Tecnologia');
INSERT INTO `java_curso`.`categorias` (`nombre`) VALUES ('Computación');

ALTER TABLE `java_curso`.`productos` 
ADD COLUMN `sku` VARCHAR(10) NULL AFTER `categoria_id`,
ADD UNIQUE INDEX `sku_UNIQUE` (`sku` ASC) VISIBLE;
;


CREATE TABLE `java_curso`.`usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(12) NULL,
  `password` VARCHAR(60) NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

INSERT INTO `java_curso`.`usuario` (`username`, `password`, `email`) VALUES ('admin', '12345', 'mail@mail.com');
INSERT INTO `java_curso`.`usuario` (`username`, `password`, `email`) VALUES ('eliel', '12345', 'mail@mail.com');


CREATE TABLE `java_curso`.`clientes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `apellido` VARCHAR(45) NULL,
  `forma_pago` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));


ALTER TABLE `java_curso`.`clientes` 
ADD COLUMN `creado_en` DATETIME NULL DEFAULT NULL AFTER `forma_pago`,
ADD COLUMN `editado_en` DATETIME NULL DEFAULT NULL AFTER `creado_en`;


CREATE TABLE `java_curso`.`instructores` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `apellido` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

INSERT INTO `java_curso`.`instructores` (`nombre`, `apellido`) VALUES ('Eliel', 'Herrera');

