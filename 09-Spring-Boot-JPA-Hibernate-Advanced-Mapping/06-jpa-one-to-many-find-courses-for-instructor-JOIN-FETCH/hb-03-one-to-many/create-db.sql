-- Drop the schema `hb-03-one-to-many` if it already exists to avoid conflicts.
DROP SCHEMA IF EXISTS `hb-03-one-to-many`;

-- Create a new schema (database) named `hb-03-one-to-many`.
CREATE SCHEMA `hb-03-one-to-many`;

-- Switch to using the newly created schema.
USE `hb-03-one-to-many`;

-- Temporarily disable foreign key checks to allow smooth table creation and dropping.
SET FOREIGN_KEY_CHECKS = 0;

-- Drop the `instructor_detail` table if it already exists.
DROP TABLE IF EXISTS `instructor_detail`;

-- Create the `instructor_detail` table to store additional information about instructors.
CREATE TABLE `instructor_detail` (
  `id`              INT NOT NULL AUTO_INCREMENT,  -- Unique identifier for each row, auto-incremented.
  `youtube_channel` VARCHAR(128) DEFAULT NULL,    -- YouTube channel URL of the instructor, optional.
  `hobby`           VARCHAR(45) DEFAULT NULL,     -- Hobby of the instructor, optional.
  PRIMARY KEY (`id`)                              -- Sets `id` as the primary key.
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- Drop the `instructor` table if it already exists.
DROP TABLE IF EXISTS `instructor`;

-- Create the `instructor` table to store basic instructor details.
CREATE TABLE `instructor` (
  `id`                  INT NOT NULL AUTO_INCREMENT,   -- Unique identifier for each row, auto-incremented.
  `first_name`          VARCHAR(45) DEFAULT NULL,      -- First name of the instructor, optional.
  `last_name`           VARCHAR(45) DEFAULT NULL,      -- Last name of the instructor, optional.
  `email`               VARCHAR(45) DEFAULT NULL,      -- Email of the instructor, optional.
  `instructor_detail_id` INT DEFAULT NULL,             -- References `id` in the `instructor_detail` table.
  PRIMARY KEY (`id`),                                  -- Sets `id` as the primary key.
  KEY `FK_DETAIL_idx` (`instructor_detail_id`),        -- Adds an index on `instructor_detail_id` for faster lookups.
  CONSTRAINT `FK_DETAIL`                               -- Defines a foreign key constraint.
    FOREIGN KEY (`instructor_detail_id`)               -- Links `instructor_detail_id` to `id` in `instructor_detail`.
    REFERENCES `instructor_detail` (`id`)             -- Establishes the relationship between the two tables.
    ON DELETE NO ACTION                                -- Prevents deletion of a referenced row.
    ON UPDATE NO ACTION                                -- Prevents updates to the referenced row.
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- Drop the `course` table if it already exists.
DROP TABLE IF EXISTS `course`;

-- Create the `course` table to store course details.
CREATE TABLE `course` (
  `id`             INT NOT NULL AUTO_INCREMENT,     -- Unique identifier for each course, auto-incremented.
  `title`          VARCHAR(128) DEFAULT NULL,       -- Title of the course, optional.
  `instructor_id`  INT DEFAULT NULL,                -- References `id` in the `instructor` table.
  PRIMARY KEY (`id`),                               -- Sets `id` as the primary key.
  UNIQUE KEY `TITLE_UNIQUE` (`title`),              -- Ensures that course titles are unique.
  KEY `FK_INSTRUCTOR_idx` (`instructor_id`),        -- Adds an index on `instructor_id` for faster lookups.
  CONSTRAINT `FK_INSTRUCTOR`                        -- Defines a foreign key constraint.
    FOREIGN KEY (`instructor_id`)                   -- Links `instructor_id` to `id` in `instructor`.
    REFERENCES `instructor` (`id`)                  -- Establishes the relationship between the two tables.
    ON DELETE NO ACTION                             -- Prevents deletion of a referenced row.
    ON UPDATE NO ACTION                             -- Prevents updates to the referenced row.
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1; -- NOTE: Starting with id = 10 

-- Re-enable foreign key checks after all tables are created.
SET FOREIGN_KEY_CHECKS = 1;
