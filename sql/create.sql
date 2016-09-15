DROP TABLE APP.CONCRETE_ITEM;
DROP TABLE APP.STORE;

create table APP.CONCRETE_ITEM (
	id INT NOT NULL,
	name varchar(20) default NULL,
	description varchar(2000) default null,
	price DOUBLE default NULL,
	PRIMARY KEY (id)
);

create table APP.STORE (
	id INT NOT NULL,
	stored_item_id INT,
	amount INT,
	PRIMARY KEY (id),
	foreign key (stored_item_id) references APP.CONCRETE_ITEM(id)
);

SELECT * from APP.CONCRETE_ITEM inner join APP.STORE on APP.CONCRETE_ITEM.ID=STORED_ITEM_ID;

INSERT INTO APP.CONCRETE_ITEM (ID,  DESCRIPTION, NAME, PRICE) values (1, 'No description', 'Phone', 200.5);
INSERT INTO APP.CONCRETE_ITEM (ID,  DESCRIPTION, NAME, PRICE) values (3, 'This is a basic tablet.', 'Tablet', 100.105);
INSERT INTO APP.CONCRETE_ITEM (ID,  DESCRIPTION, NAME, PRICE) values (2, 'A new television.', 'TV Smart 2.1', 1000.0);

INSERT INTO APP.STORE (id, stored_item_id, amount) values (1, 1, 100);
INSERT INTO APP.STORE (id, stored_item_id, amount) values (2, 2, 4);
INSERT INTO APP.STORE (id, stored_item_id, amount) values (3, 3, 0);

SELECT * from APP.CONCRETE_ITEM;
SELECT * from APP.STORE;
