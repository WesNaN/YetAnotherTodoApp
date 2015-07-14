-- -----------------------------------------------------
-- Table `GitObjects`.`repository`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `repository` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `color` VARCHAR(10) NOT NULL,
  `start` DATE NOT NULL,
  `end` DATE NULL,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(500) NULL,
    PRIMARY KEY (`id`)
  );

-- -----------------------------------------------------
-- Table `GitObjects`.`milestone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `milestone` (
  `id`         INT         NOT NULL,
  `plannedend` DATE        NOT NULL,
  `name`       VARCHAR(45) NOT NULL,
  `description` VARCHAR(500) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `repo`
    FOREIGN KEY (`id`) REFERENCES `repository` (`id`)
  );

-- -----------------------------------------------------
-- Table `GitObjects`.`issue`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `issue` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `priority` TINYINT(1) NULL,
  `finished` BINARY(1) NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(500) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `connectedrepo`
    FOREIGN KEY (`id`) REFERENCES `repository` (`id`)
  );

-- -----------------------------------------------------
-- Table `GitObjects`.`milestoneRegistry`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `milestoneRegistry` (
  mile INT NOT NULL ,
  issues INT NOT NULL ,
  PRIMARY KEY (mile, issues),
  CONSTRAINT `mile`
    FOREIGN KEY (`mile`) REFERENCES `milestone` (`id`),
  CONSTRAINT `issues`
    FOREIGN KEY (`issues`) REFERENCES `issue` (`id`),
  );