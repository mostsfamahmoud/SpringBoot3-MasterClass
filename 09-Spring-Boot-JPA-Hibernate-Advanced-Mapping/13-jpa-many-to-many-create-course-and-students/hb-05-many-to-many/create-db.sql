-- Drop the schema if it already exists to start fresh
DROP SCHEMA IF EXISTS `hb-05-many-to-many`;

-- Create a new schema named `hb-05-many-to-many`
CREATE SCHEMA `hb-05-many-to-many`;

-- Switch to the newly created schema
USE `hb-05-many-to-many`;

-- Disable foreign key checks to allow table creation without constraints validation
SET FOREIGN_KEY_CHECKS = 0;

-- =============================
-- Table: `instructor_detail`
-- =============================
CREATE TABLE `instructor_detail` (
    `id` INT NOT NULL AUTO_INCREMENT,         -- Primary key, auto-incremented
    `youtube_channel` VARCHAR(128) DEFAULT NULL, -- Optional YouTube channel URL
    `hobby` VARCHAR(45) DEFAULT NULL,        -- Optional hobby description
    PRIMARY KEY (`id`)                       -- Define the primary key for the table
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- =============================
-- Table: `instructor`
-- =============================
CREATE TABLE `instructor` (
    `id` INT NOT NULL AUTO_INCREMENT,           -- Primary key, auto-incremented
    `first_name` VARCHAR(45) DEFAULT NULL,      -- Optional first name
    `last_name` VARCHAR(45) DEFAULT NULL,       -- Optional last name
    `email` VARCHAR(45) DEFAULT NULL,           -- Optional email address
    `instructor_detail_id` INT DEFAULT NULL,    -- Foreign key to `instructor_detail`
    PRIMARY KEY (`id`),                         -- Define the primary key for the table
    KEY `FK_DETAIL_idx` (`instructor_detail_id`), -- Index for the foreign key
    CONSTRAINT `FK_DETAIL` FOREIGN KEY (`instructor_detail_id`) -- Define the foreign key constraint
        REFERENCES `instructor_detail` (`id`)   -- Reference `id` column in `instructor_detail`
        ON DELETE NO ACTION                     -- No action on delete
        ON UPDATE NO ACTION                     -- No action on update
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- =============================
-- Table: `course`
-- =============================
CREATE TABLE `course` (
    `id` INT NOT NULL AUTO_INCREMENT,            -- Primary key, auto-incremented
    `title` VARCHAR(128) DEFAULT NULL,           -- Course title (unique constraint applied)
    `instructor_id` INT DEFAULT NULL,            -- Foreign key to `instructor`
    PRIMARY KEY (`id`),                          -- Define the primary key for the table
    UNIQUE KEY `TITLE_UNIQUE` (`title`),         -- Unique constraint on the title
    KEY `FK_INSTRUCTOR_idx` (`instructor_id`),   -- Index for the foreign key
    CONSTRAINT `FK_INSTRUCTOR` FOREIGN KEY (`instructor_id`) -- Foreign key constraint
        REFERENCES `instructor` (`id`)           -- Reference `id` column in `instructor`
        ON DELETE NO ACTION                      -- No action on delete
        ON UPDATE NO ACTION                      -- No action on update
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- =============================
-- Table: `review`
-- =============================
CREATE TABLE `review` (
    `id` INT NOT NULL AUTO_INCREMENT,          -- Primary key, auto-incremented
    `comment` VARCHAR(256) DEFAULT NULL,       -- Optional comment/review content
    `course_id` INT DEFAULT NULL,              -- Foreign key to `course`
    PRIMARY KEY (`id`),                        -- Define the primary key for the table
    KEY `FK_COURSE_ID_idx` (`course_id`),      -- Index for the foreign key
    CONSTRAINT `FK_COURSE` FOREIGN KEY (`course_id`) -- Foreign key constraint
        REFERENCES `course` (`id`)             -- Reference `id` column in `course`
        ON DELETE NO ACTION                    -- No action on delete
        ON UPDATE NO ACTION                    -- No action on update
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- =============================
-- Table: `student`
-- =============================
CREATE TABLE `student` (
    `id` INT NOT NULL AUTO_INCREMENT,          -- Primary key, auto-incremented
    `first_name` VARCHAR(45) DEFAULT NULL,     -- Optional first name
    `last_name` VARCHAR(45) DEFAULT NULL,      -- Optional last name
    `email` VARCHAR(45) DEFAULT NULL,          -- Optional email address
    PRIMARY KEY (`id`)                         -- Define the primary key for the table
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- =============================
-- Table: `course_student`
-- Many-to-Many relationship table for `course` and `student`
-- =============================
CREATE TABLE `course_student` (
    `course_id` INT NOT NULL,                  -- Foreign key to `course`
    `student_id` INT NOT NULL,                 -- Foreign key to `student`
    PRIMARY KEY (`course_id`, `student_id`),   -- Composite primary key for many-to-many relationship
    KEY `FK_STUDENT_idx` (`student_id`),       -- Index for the foreign key to `student`
    CONSTRAINT `FK_COURSE_05` FOREIGN KEY (`course_id`) -- Foreign key constraint for `course`
        REFERENCES `course` (`id`)             -- Reference `id` column in `course`
        ON DELETE NO ACTION                    -- No action on delete
        ON UPDATE NO ACTION,                   -- No action on update
    CONSTRAINT `FK_STUDENT` FOREIGN KEY (`student_id`) -- Foreign key constraint for `student`
        REFERENCES `student` (`id`)            -- Reference `id` column in `student`
        ON DELETE NO ACTION                    -- No action on delete
        ON UPDATE NO ACTION                    -- No action on update
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Re-enable foreign key checks after table creation
SET FOREIGN_KEY_CHECKS = 1;
