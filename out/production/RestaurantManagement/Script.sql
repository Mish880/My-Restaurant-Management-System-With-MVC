DROP TABLE IF EXISTS Admin;
CREATE TABLE IF NOT EXISTS Admin(
id VARCHAR (15),
name VARCHAR (15),
Password VARCHAR (45),
CONSTRAINT PRIMARY KEY (id)
);

Show tables;
desc Admin;
#-----------------------------------
DROP TABLE IF EXISTS Waiter;
CREATE TABLE IF NOT EXISTS Waiter(
id VARCHAR (15),
name VARCHAR (15),
password VARCHAR (45),
CONSTRAINT PRIMARY KEY (id)
);

Show tables;
desc  Waiter;
#-------------------------------------
DROP TABLE IF EXISTS  Employee;
CREATE TABLE IF NOT EXISTS Employee(
id VARCHAR(15),
name VARCHAR (45),
Email VARCHAR (45),
address TEXT,
password VARCHAR(45),
CONSTRAINT PRIMARY KEY (id)
);

Show tables;
desc Employee;
#--------------------------------
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
DROP TABLE IF EXISTS ROOMS;
CREATE TABLE IF NOT EXISTS ROOMS(
Rcode VARCHAR(15),
ROOMTYPE TEXT,
qtyOnHand INT DEFAULT 0,
UnitPrice DOUBLE DEFAULT 0.00,
CONSTRAINT PRIMARY KEY (Rcode)
 );

show tables;
Desc ROOMS;
#---------------------------------------
DROP TABLE IF EXISTS FOODS;
CREATE TABLE IF NOT EXISTS FOODS(
Fcode VARCHAR(15),
FOODTYPE TEXT,
qtyOnHand INT DEFAULT 0,
UnitPrice DOUBLE DEFAULT 0.00,
CONSTRAINT PRIMARY KEY (Fcode)
 );

show tables;
Desc FOODS;
#-------------------------------
DROP TABLE IF EXISTS `Order Detail`;
CREATE TABLE IF NOT EXISTS OrderDetail(
FOODSitemCode VARCHAR (15),
ROOMSitemCode VARCHAR (15),
orderId VARCHAR (15),
qty INT,
price DOUBLE,
CONSTRAINT PRIMARY KEY (FOODSitemCode,ROOMSitemCode,orderId),
CONSTRAINT FOREIGN KEY (FOODSitemCode) REFERENCES FOODS(Fcode) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT FOREIGN KEY (ROOMSitemCode) REFERENCES ROOMS(Rcode) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT FOREIGN KEY (orderId) REFERENCES `Order`(orderId) ON DELETE CASCADE ON UPDATE CASCADE
);
SHOW TABLES ;
Desc  Order Detail;
#-----------------------------------

