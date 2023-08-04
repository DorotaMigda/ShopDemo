insert into users (id, firstname, lastname, dateofbirth, email, password) values (nextval('user_id_seq'), 'Jan', 'Kowalski', parsedatetime('11/04/1999', 'dd/MM/yyyy'), 'jkowalski@gmail.com', 'password');
insert into users (id, firstname, lastname, dateofbirth, email, password) values (nextval('user_id_seq'), 'Anna', 'Nowak', parsedatetime('07/02/1992', 'dd/MM/yyyy'), 'anowak@gmail.com', 'nowak');
insert into users (id, firstname, lastname, dateofbirth, email, password) values (nextval('user_id_seq'), 'Krzysztof', 'Urban', parsedatetime('11/09/1977', 'dd/MM/yyyy'), 'kurban@gmail.com', 'urban');


-- admin
insert into admins (id, firstname, lastname, dateofbirth, email, password, role) values (nextval('admin_id_seq'), 'Adam', 'Admin', parsedatetime('10/02/1975', 'dd/MM/yyyy'), 'admin@gmail.com', 'admin','administrator');

insert into Product (id, name, description, price, quantityMax) values (nextval('prod_id_seq'),'Yellow Dress','Dress M',249.99,100);
insert into Product (id, name, description, price, quantityMax) values (nextval('prod_id_seq'),'Blue Skirt','Skirt 38',35, 1);
insert into Product (id, name, description, price, quantityMax) values (nextval('prod_id_seq'),'Green Blouse','Blouse 40,42,50', 49.99, 5);
insert into Product (id, name, description, price, quantityMax) values (nextval('prod_id_seq'),'Red Skirt','Skirt 40', 150.99,7);
insert into Product (id, name, description, price, quantityMax) values (nextval('prod_id_seq'),'Red Blouse','Blouse 40', 150.99,1);
insert into Product (id, name, description, price, quantityMax) values (nextval('prod_id_seq'),'Red Dress','Dress M,L',250,11);
insert into Product (id, name, description, price,quantityMax) values (nextval('prod_id_seq'),'Brown Jacket','Jacket M,L,XL,XXL',350,1);
insert into Product (id, name, description, price,quantityMax) values (nextval('prod_id_seq'),'Black Trousers','Trousers 38,40,42,44', 49.99,2);
