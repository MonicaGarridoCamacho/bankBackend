<<<<<<< HEAD
CREATE DATABASE BANKING;
CONNECT TO BANKING USER db2inst1 USING db2inst1;
CREATE TABLE Transactions(
	transactionId VARCHAR(150) GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	accountId VARCHAR(150) NOT NULL,
	transactionInformation VARCHAR(150) NOT NULL,
	addressLine VARCHAR(150),
	amount VARCHAR(5),
	PRIMARY KEY (transactionId)
);
=======
CREATE DATABASE BANKING;
CONNECT TO BANKING USER db2inst1 USING db2inst1;
CREATE TABLE Transactions(
	transactionId VARCHAR(150) GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	accountId VARCHAR(150) NOT NULL,
	transactionInformation VARCHAR(150) NOT NULL,
	addressLine VARCHAR(150),
	amount VARCHAR(5),
	PRIMARY KEY (transactionId)
);
>>>>>>> 4db42df2449275f7641baf353241e61571914b5f
