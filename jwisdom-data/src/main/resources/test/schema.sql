DROP TABLE IF EXISTS car_info;
DROP TABLE IF EXISTS user;
CREATE TABLE IF NOT EXISTS car_info
(
  id int PRIMARY KEY AUTO_INCREMENT,
  image_url CHAR(200) NOT NULL,
  rate FLOAT DEFAULT 0.0
);

CREATE TABLE IF NOT EXISTS user (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name CHAR(100) NOT NULL,
  age INT,
  gender char(2)
);