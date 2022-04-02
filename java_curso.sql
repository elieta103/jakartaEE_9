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

