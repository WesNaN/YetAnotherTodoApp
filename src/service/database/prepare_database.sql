DROP TABLE IF EXISTS Tasks;
DROP TABLE IF EXISTS Reminders;
DROP TABLE IF EXISTS Projects;
DROP TABLE IF EXISTS Calendars;
DROP TABLE IF EXISTS LastEdits;

CREATE TABLE Calendars
(
  id INT PRIMARY KEY NOT NULL,
  name VARCHAR(255)
);

CREATE TABLE Projects
(
  id   INT PRIMARY KEY NOT NULL,
  name VARCHAR(255)
);

CREATE TABLE Tasks
(
  id          INT PRIMARY KEY          NOT NULL,
  title       VARCHAR(255)             NOT NULL,
  description VARCHAR(255),
  priority    INT,
  label       VARCHAR(255),
  due         TIMESTAMP,
  project_id  INT REFERENCES Projects (id),
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



