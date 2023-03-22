CREATE DATABASE BANKING
CONNECT TO BANKING USER db2inst1 USING db2inst1;
CREATE TABLE Transactions( transactionId VARCHAR(150) NOT NULL, accountId VARCHAR(150) NOT NULL, transactionInformation VARCHAR(150) NOT NULL, addressLine VARCHAR(150), amount VARCHAR(5), PRIMARY KEY (transactionId));
INSERT INTO Transactions (transactionId, accountId, transactionInformation, addressLine, amount) VALUES ('1', '01039482', 'Insurance Receipt', 'Howard Street', '50');
INSERT INTO Transactions (transactionId, accountId, transactionInformation, addressLine, amount) VALUES ('2', '14879638', 'Salary', 'Cherry Court', '1500');
INSERT INTO Transactions (transactionId, accountId, transactionInformation, addressLine, amount) VALUES ('3', '98756510', 'Credit Receipt', 'Glasgow Street', '500');
