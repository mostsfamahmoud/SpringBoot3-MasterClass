-- Drop the schema `hb-01-one-to-one-uni` if it already exists to avoid conflicts.
DROP SCHEMA IF EXISTS `hb-01-one-to-one-uni`;

-- Create a new schema (database) named `hb-01-one-to-one-uni`.
CREATE SCHEMA `hb-01-one-to-one-uni`;

-- Switch to using the newly created schema.
USE `hb-01-one-to-one-uni`;

-- Disable foreign key checks temporarily to allow the creation of tables without constraints issues.
SET FOREIGN_KEY_CHECKS = 0;

-- Create the `instructor_detail` table to store additional information about instructors.
CREATE TABLE `instructor_detail` (
  `id`                int NOT NULL AUTO_INCREMENT,      -- Unique identifier for each row, auto-incremented.
  `youtube_channel`   varchar(128) DEFAULT NULL,        -- Stores the YouTube channel URL of the instructor, optional.
  `hobby`             varchar(45) DEFAULT NULL,         -- Stores the hobby of the instructor, optional.
  PRIMARY KEY (`id`)                                    -- Sets the `id` column as the primary key.
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1; -- Table engine and default character set.

-- Create the `instructor` table to store basic instructor details.
CREATE TABLE `instructor` (
  `id`                    int NOT NULL AUTO_INCREMENT,      -- Unique identifier for each row, auto-incremented.
  `first_name`            varchar(45) DEFAULT NULL,         -- Stores the first name of the instructor, optional.
  `last_name`             varchar(45) DEFAULT NULL,         -- Stores the last name of the instructor, optional.
  `email`                 varchar(45) DEFAULT NULL,         -- Stores the email address of the instructor, optional.
  `instructor_detail_id`  int DEFAULT NULL,                 -- References the `id` column in the `instructor_detail` table.
  PRIMARY KEY (`id`),                                       -- Sets the `id` column as the primary key.
  KEY `FK_DETAIL_idx` (`instructor_detail_id`),             -- Adds an index on the `instructor_detail_id` column for faster lookups.
  CONSTRAINT `FK_DETAIL` FOREIGN KEY (`instructor_detail_id`) -- Defines a foreign key constraint.
  REFERENCES `instructor_detail` (`id`)                    -- Links `instructor_detail_id` to the `id` column in the `instructor_detail` table.
  ON DELETE NO ACTION ON UPDATE NO ACTION                  -- Specifies no action on delete or update of the referenced row.
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;   -- Table engine and default character set.

-- Re-enable foreign key checks after table creation to ensure constraints are enforced.
SET FOREIGN_KEY_CHECKS = 1;
