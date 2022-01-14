DROP TABLE IF EXISTS  login;
CREATE TABLE IF NOT EXISTS login(
id VARCHAR (15),
Password VARCHAR (15),
role VARCHAR  (45),
CONSTRAINT PRIMARY KEY (id)
);

Show tables;
desc login;
#-----------------------------------

DROP TABLE IF EXISTS Customer;
CREATE TABLE IF NOT EXISTS Customer(
id VARCHAR (15),
name VARCHAR (45),
address TEXT,
Salary DOUBLE DEFAULT 0.00,
CONSTRAINT PRIMARY KEY (id)
);

Show tables;
Desc Customer;
#-----------------------------
DROP TABLE IF EXISTS `Order`;
CREATE TABLE IF NOT EXISTS `Order`(
orderId VARCHAR(15),
cId VARCHAR(15),
orderDate DATE,
time VARCHAR (15),
cost DOUBLE,
CONSTRAINT PRIMARY KEY (orderId),
CONSTRAINT FOREIGN KEY (cId) REFERENCES Customer(id) ON DELETE CASCADE ON UPDATE CASCADE
);

show tables;
Desc `Order`;
#-----------------------------------
DROP TABLE IF EXISTS rorder;
CREATE TABLE IF NOT EXISTS rorder(
orderId VARCHAR(15),
cId VARCHAR(15),
orderDate DATE,
time VARCHAR (15),
cost DOUBLE,
CONSTRAINT PRIMARY KEY (orderId),
CONSTRAINT FOREIGN KEY (cId) REFERENCES Customer(id) ON DELETE CASCADE ON UPDATE CASCADE
);
show tables;
desc rorder;
#-----------------------------------
DROP TABLE IF EXISTS  rooms;
CREATE TABLE IF NOT EXISTS  rooms(
Rcode VARCHAR(15),
ROOMTYPE TEXT,
qtyOnHand INT DEFAULT 0,
UnitPrice DOUBLE DEFAULT 0.00,
CONSTRAINT PRIMARY KEY (Rcode)
 );
show tables;
Desc rooms;
#---------------------------------------
DROP TABLE IF EXISTS foods;
CREATE TABLE IF NOT EXISTS foods(
Fcode VARCHAR(15),
FOODTYPE TEXT,
qtyOnHand INT DEFAULT 0,
UnitPrice DOUBLE DEFAULT 0.00,
CONSTRAINT PRIMARY KEY (Fcode)
 );

show tables;
Desc foods;
#-------------------------------
DROP TABLE IF EXISTS orderdetail;
FOODSitemCode VARCHAR (15),
orderId VARCHAR (15),
qty INT,
price DOUBLE,
CONSTRAINT PRIMARY KEY (FOODSitemCode,orderId),
CONSTRAINT FOREIGN KEY (FOODSitemCode) REFERENCES foods(Fcode) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT FOREIGN KEY (orderId) REFERENCES `Order`(orderId) ON DELETE CASCADE ON UPDATE CASCADE
);
SHOW TABLES;
desc orderdetail;
#----------------------------------------
DROP TABLE IF EXISTS orderdetailo;
ROOMSitemCode VARCHAR (15),
orderId VARCHAR (15),
qty INT,
price DOUBLE,
CONSTRAINT PRIMARY KEY (ROOMSitemCode,orderId),
CONSTRAINT FOREIGN KEY (ROOMSitemCode) REFERENCES rooms(Fcode) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT FOREIGN KEY (orderId) REFERENCES rorder(orderId) ON DELETE CASCADE ON UPDATE CASCADE
);
SHOW TABLES;
desc orderdetail;
#---------------------------------------
