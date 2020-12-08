CREATE TABLE users(id int AUTO_INCREMENT NOT NULL UNIQUE PRIMARY KEY UNSIGNED, name varchar(50), password varchar, email varchar(50) );
CREATE TABLE wallet(id int AUTO_INCREMENT NOT NULL UNIQUE PRIMARY KEY UNSIGNED, name varchar(50), value numeric(10,2) );
CREATE TABLE users_wallet(id int AUTO_INCREMENT NOT NULL UNIQUE PRIMARY KEY UNSIGNED, wallet int, users int, FOREIGN KEY (users) REFERENCES users(id), FOREIGN KEY (wallet) REFERENCES wallet(id) );
CREATE TABLE wallet_items(id int AUTO_INCREMENT NOT NULL UNIQUE PRIMARY KEY UNSIGNED, wallet int, 'data' date, 'type' varchar(2), description varchar(500), value numeric(10,2), FOREIGN KEY (wallet) REFERENCES wallet(id) );
