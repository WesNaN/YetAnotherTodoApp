DROP TABLE IF EXISTS Tasks;

CREATE TABLE Tasks
(
  id         INT AUTO_INCREMENT,
  name       VARCHAR(255),
  content    VARCHAR(255),
  priority   INT,
  label      VARCHAR(255),
  project_id INT
);




