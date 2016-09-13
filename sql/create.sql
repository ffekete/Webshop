DROP TABLE APP.CONCRETE_ITEM;

create table APP.CONCRETE_ITEM (
	id INT NOT NULL,
	name varchar(20) default NULL,
	description varchar(2000) default null,
	price DOUBLE default NULL,
	availability BOOLEAN default NULL,
	PRIMARY KEY (id)
);

INSERT INTO APP.CONCRETE_ITEM (ID, AVAILABILITY, DESCRIPTION, NAME, PRICE) values (1, TRUE, 'No description', 'Phone', 200.5);
INSERT INTO APP.CONCRETE_ITEM (ID, AVAILABILITY, DESCRIPTION, NAME, PRICE) values (3, TRUE, 'This is a basic tablet.', 'Tablet', 100.105);
INSERT INTO APP.CONCRETE_ITEM (ID, AVAILABILITY, DESCRIPTION, NAME, PRICE) values (2, FALSE, 'A new television.', 'TV Smart 2.1', 1000.0);

SELECT * from APP.CONCRETE_ITEM;
