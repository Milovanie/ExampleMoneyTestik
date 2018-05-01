DROP TABLE IF EXISTS Povar;


CREATE TABLE IF NOT EXISTS Povar (
  povarid int(100) NOT NULL AUTO_INCREMENT,
  povar_name varchar(50) DEFAULT NULL,
  roll_number varchar(50) DEFAULT NULL,
  course varchar(50) DEFAULT NULL,  
  PRIMARY KEY (povarid)
);