DROP TABLE IF EXISTS Customers;
CREATE TABLE IF NOT EXISTS Customers (
  id int NOT NULL,
  name varchar(60)  NOT NULL,
  address varchar(60)  NOT NULL,
  city varchar(30)  NOT NULL,
  state varchar(10)  NOT NULL,
  zip int NOT NULL,
  PRIMARY KEY (id)
);
