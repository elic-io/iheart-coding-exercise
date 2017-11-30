CREATE TABLE advertiser (
    id INT PRIMARY KEY auto_increment(1,1),
    name VARCHAR,
    contactName VARCHAR,
    creditLimit DECIMAL(20,2),
    tombStone BOOLEAN DEFAULT FALSE
);

insert into advertiser (name, contactName, creditLimit) values ('Super Soaker Inc.', 'Lonnie Johnson', 2000000.00);
