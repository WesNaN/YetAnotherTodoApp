DROP TABLE IF EXISTS Tasks;
DROP TABLE IF EXISTS Reminders;
DROP TABLE IF EXISTS Projects;
DROP TABLE IF EXISTS Calendars;
DROP TABLE IF EXISTS LastEdits;


CREATE TABLE Calendars
(
  id    INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  name  VARCHAR(100),
  color VARCHAR(20)
);

CREATE TABLE Projects
(
  id   INT PRIMARY KEY NOT NULL,
  name VARCHAR(255)
);

CREATE TABLE Tasks
(
  id          INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  title       VARCHAR(255)                   NOT NULL,
  description VARCHAR(500),
  location    VARCHAR(200),
  label       VARCHAR(255),
  due         TIMESTAMP,
  lastedit    TIMESTAMP,
  priority    TINYINT,
  calendar_id INT REFERENCES Calendars (id)
);

CREATE TABLE Reminders
(
  id       INT AUTO_INCREMENT NOT NULL,
  task_id  INT                NOT NULL REFERENCES Tasks (id),
  reminder TIMESTAMP
);


CREATE TABLE LastEdits
(
  id       INT AUTO_INCREMENT NOT NULL,
  task_id  INT                NOT NULL REFERENCES Tasks (id),
  reminder TIMESTAMP
);






