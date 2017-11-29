CREATE TABLE advertiser (
    id INT PRIMARY KEY auto_increment,
    name VARCHAR,
    contactName VARCHAR,
    creditLimit DECIMAL(20,2)
);

insert into advertiser (name, contactName, creditLimit) values ('Super Soaker Inc.', 'Lonnie Johnson', 2000000.00);
