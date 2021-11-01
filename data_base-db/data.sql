drop table if exists account CASCADE;
drop table if exists category CASCADE;
drop table if exists comment CASCADE;
drop table if exists review CASCADE;
drop table if exists payment_type CASCADE;
drop table if exists purchase_order CASCADE;
drop table if exists shop CASCADE;
drop table if exists delivery CASCADE;
drop table if exists payment_type_deliveries CASCADE;
drop table if exists product CASCADE;
drop table if exists category_products CASCADE;
drop table if exists shopping_cart CASCADE;
drop table if exists shopping_cart_product CASCADE;
drop table if exists status CASCADE;

CREATE TABLE IF NOT EXISTS account (
    id bigserial PRIMARY KEY,
    name character varying(255),
    address character varying(255),
    city character varying(255),
    country character varying(255),
    email character varying(255),
    phone_number bigint not null,
    postal_code integer not null,
    shopping_cart_id bigint
);

CREATE TABLE IF NOT EXISTS category (
    id bigserial PRIMARY KEY,
    name character varying(255)
);

CREATE TABLE IF NOT EXISTS comment (
    id bigserial PRIMARY KEY,
    account_id bigint not null,
    message character varying(255),
    product_id bigint
);

CREATE TABLE IF NOT EXISTS review (
    id bigserial PRIMARY KEY,
    account_id bigint not null,
    message character varying(255),
    product_id bigint
);


CREATE TABLE IF NOT EXISTS payment_type (
    id bigserial PRIMARY KEY,
    type_name character varying(255)
);

CREATE TABLE IF NOT EXISTS purchase_order (
    id bigserial PRIMARY KEY,
    order_time timestamp,
    status character varying(255),
    sum decimal(19,2),
    shopping_cart_id bigint,
    delivery_id bigint
);

CREATE TABLE IF NOT EXISTS shop (
    id bigserial PRIMARY KEY,
    name character varying(255),
    city character varying(255),
    address character varying(255),
    phone_number bigint not null,
    working_hours character varying(255),
    delivery_id bigint
);

CREATE TABLE IF NOT EXISTS delivery (
    id bigserial PRIMARY KEY,
    delivery_time timestamp,
    payment_type_id bigint,
    purchase_order_id bigint,
    shop_id bigint,
    FOREIGN KEY (payment_type_id) REFERENCES payment_type(id),
    FOREIGN KEY (purchase_order_id) REFERENCES purchase_order(id),
    FOREIGN KEY (shop_id) REFERENCES shop(id)
);

CREATE TABLE IF NOT EXISTS payment_type_deliveries (
    payment_type_id bigint NOT NULL,
    deliveries_id bigint NOT NULL,
    PRIMARY KEY (deliveries_id),
    FOREIGN KEY (deliveries_id) REFERENCES delivery(id),
    FOREIGN KEY (payment_type_id) REFERENCES payment_type(id)
);

CREATE TABLE IF NOT EXISTS product (
    id bigserial PRIMARY KEY,
    name character varying(255),
    description character varying(255),
    price decimal(19,2),
    rating double precision,
    account_seller_id bigint not null,
    category_id bigint,
    comment_id bigint,
    review_id bigint,
    FOREIGN KEY (category_id) REFERENCES category(id),
    FOREIGN KEY (comment_id) REFERENCES comment(id),
    FOREIGN KEY (review_id) REFERENCES review(id)
);

CREATE TABLE IF NOT EXISTS category_products (
    category_id bigint NOT NULL,
    product_id bigint NOT NULL,
    PRIMARY KEY (product_id),
    FOREIGN KEY (product_id) REFERENCES product(id),
    FOREIGN KEY (category_id) REFERENCES category(id)
);

CREATE TABLE IF NOT EXISTS shopping_cart (
    id bigserial PRIMARY KEY,
    account_id bigint,
    purchase_order_id bigint,
    FOREIGN KEY (account_id) REFERENCES account(id),
    FOREIGN KEY (purchase_order_id) REFERENCES purchase_order(id)
);

CREATE TABLE IF NOT EXISTS shopping_cart_product (
    shopping_cart_id bigint NOT NULL,
    product_id bigint NOT NULL,
    PRIMARY KEY (shopping_cart_id, product_id),
    FOREIGN KEY (shopping_cart_id) REFERENCES shopping_cart(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);

CREATE TABLE IF NOT EXISTS status (
    id bigserial PRIMARY KEY,
    name character varying(255),
    purchase_order_id bigint,
    FOREIGN KEY (purchase_order_id) REFERENCES purchase_order(id)
);

ALTER TABLE purchase_order ADD COLUMN IF NOT EXISTS shopping_cart_id bigint REFERENCES shopping_cart(id);
ALTER TABLE purchase_order ADD COLUMN IF NOT EXISTS delivery_id bigint REFERENCES delivery(id);
ALTER TABLE shop ADD COLUMN IF NOT EXISTS delivery_id bigint REFERENCES delivery(id);
ALTER TABLE account ADD COLUMN IF NOT EXISTS shopping_cart_id bigint REFERENCES shopping_cart(id);
ALTER TABLE comment ADD COLUMN IF NOT EXISTS product_id bigint REFERENCES product(id);
ALTER TABLE review ADD COLUMN IF NOT EXISTS product_id bigint REFERENCES product(id);

insert into account (name, address, city, country, email, phone_number, postal_code)
values('Julia Petrova', 'Moskovskaya str, 266, 15', 'Brest', 'Belarus', 'elisaveta@mail.ru' , '375295768493', '224008');
insert into account (name, address, city, country, email, phone_number, postal_code)
VALUES('Oksana Zaitceva','Volodarskaya str, 314, 63', 'Minsk', 'Belarus', 'oksana@mail.ru',  '375293647759', '220435');
insert into account (name, address, city, country, email, phone_number, postal_code)
VALUES('Anton Sergeev', 'Sovetskaya str, 34, 29', 'Slonim', 'Belarus', 'anton@mail.ru' , '375293946138', '226028');
insert into account (name, address, city, country, email, phone_number, postal_code)
VALUES('Vladimir Soloviev', 'Zamkovaya str, 34, 12', 'Minsk', 'Belarus', 'vladimir@mail.ru' , '375295629015', '220143');
insert into account (name, address, city, country, email, phone_number, postal_code)
VALUES('Maksim Serov', 'Malaya str, 21, 10', 'Pinsk', 'Belarus', 'maksim@mail.ru' , '375297415253', '225637');
insert into account (name, address, city, country, email, phone_number, postal_code)
VALUES('Sergey Kovalev', 'Parkovaya str, 58, 30', 'Grodno', 'Belarus', 'sergey@mail.ru' , '375447816377', '226103');
insert into account (name, address, city, country, email, phone_number, postal_code)
VALUES('Askona', 'Angarskaya str, 48', 'Minsk', 'Belarus', 'askona@mail.ru' , '375447349845', '220056');
insert into account (name, address, city, country, email, phone_number, postal_code)
VALUES('Solo', 'Sadovaya str, 28', 'Minsk', 'Belarus', 'solo@mail.ru' , '375296733954', '220523');
insert into account (name, address, city, country, email, phone_number, postal_code)
VALUES('Prima', 'Solnechnaya str, 14', 'Pinsk', 'Belarus', 'prima@mail.ru' , '375295637730', '225439');

insert into category (name)
values('sofa');
insert into category (name)
values('table');
insert into category (name)
values('armchair');
insert into category (name)
values('chair');
insert into category (name)
values('bed');

insert into comment (account_id, message)
values('1', 'This is good product');
insert into comment (account_id, message)
values('2', 'This is very good product');
insert into comment (account_id, message)
values('3', 'This is not good product');
insert into comment (account_id, message)
values('4', 'This is wonderful product');
insert into comment (account_id, message)
values('5', 'This is product high quality');
insert into comment (account_id, message)
values('1', 'This is good product low quality');

insert into review (account_id, message)
values('1', 'This is good service');
insert into review (account_id, message)
values('2', 'This is not good service');
insert into review (account_id, message)
values('3', 'I am satisfied with service');
insert into review (account_id, message)
values('4', 'I am satisfied very match with service');
insert into review (account_id, message)
values('5', 'Thank you for your service');
insert into review (account_id, message)
values('1', 'I am not satisfied with service');

insert into payment_type (type_name)
values('cash');
insert into payment_type (type_name)
values('card');
insert into payment_type (type_name)
values('credit card');

insert into delivery (delivery_time)
values('2021-10-17');
insert into delivery (delivery_time)
values('2021-10-24');
insert into delivery (delivery_time)
values('2021-10-20');
insert into delivery (delivery_time)
values('2021-10-17');

insert into payment_type_deliveries (payment_type_id, deliveries_id)
values('1', '1');
insert into payment_type_deliveries (payment_type_id, deliveries_id)
values('2', '2');
insert into payment_type_deliveries (payment_type_id, deliveries_id)
values('1', '3');
insert into payment_type_deliveries (payment_type_id, deliveries_id)
values('3', '4');

insert into product (name, description, price, rating, category_id, account_seller_id, comment_id, review_id)
values('sofa Amatti', 'Big sofa 3200*1000*1000', '4900', '4.8','1', '7', '5', '5');
insert into product (name, description, price, rating, category_id, account_seller_id, comment_id, review_id)
values('sofa Altera', 'Standard classic sofa 2200*1000*1000', '3200', '4.5', '1', '7', '3', '3');
insert into product (name, description, price, rating, category_id, account_seller_id, comment_id, review_id)
values('table Classic', 'Classic table 1500*850*750', '2000', '4.4','2', '8', '1', '1');
insert into product (name, description, price, rating, category_id, account_seller_id, comment_id, review_id)
values('table Optima', 'Compact table 1200*750*750', '1400', '4.9', '2', '8', '2', '2');
insert into product (name, description, price, rating, category_id, account_seller_id, comment_id, review_id)
values('armchair Credo', 'Classic armchair 900*950*800', '1500', '4.2', '3', '7', '6', '6');
insert into product (name, description, price, rating, category_id, account_seller_id, comment_id, review_id)
values('armchair Modum', 'Modern armchair 1000*850*900', '1600', '4.6', '3', '7', '4', '4');
insert into product (name, description, price, rating, category_id, account_seller_id, comment_id, review_id)
values('chair Condor', 'Classic chair 500*600*1000', '900', '4.1', '4', '8', '5', '5');
insert into product (name, description, price, rating, category_id, account_seller_id, comment_id, review_id)
values('chair Vinotti', 'Modern chair 500*600*900', '1000', '4.0', '4', '8', '3', '3');
insert into product (name, description, price, rating, category_id, account_seller_id, comment_id, review_id)
values('bed Lumia', 'Classic bed 1960*2100', '1300', '4.4', '5', '9', '1', '1');
insert into product (name, description, price, rating, category_id, account_seller_id, comment_id, review_id)
values('bed Edem', 'Modern bed 1700*2100', '1400', '4.6', '5', '9', '5', '5');

insert into purchase_order (order_time, status, sum, delivery_id)
values('2021-10-15', '1', '4900', '1');
insert into purchase_order (order_time, status, sum, delivery_id)
values('2021-10-20', '1', '3200', '2');
insert into purchase_order (order_time, status, sum, delivery_id)
values('2021-10-16', '1', '1500', '3');
insert into purchase_order (order_time, status, sum, delivery_id)
values('2021-10-12', '1', '900', '4');

insert into shop (name, city, address, phone_number, working_hours)
values('Calypso1', 'Minsk', 'Dzerginskogo str, 205', '375293764958', 'Mon - Fri 10.00 - 18.00, Sat - Sun 10.00 - 16.00');
insert into shop (name, city, address, phone_number, working_hours)
values('Calypso2', 'Minsk', 'Yakuba Kolasa str, 53', '375447857499', 'Mon - Fri 10.00 - 18.00, Sat - Sun 10.00 - 16.00');
insert into shop (name, city, address, phone_number, working_hours)
values('Calypso3', 'Minsk', 'Esenina str, 56', '375295738298', 'Mon - Fri 10.00 - 18.00, Sat - Sun 10.00 - 16.00');
insert into shop (name, city, address, phone_number, working_hours)
values('Calypso4', 'Brest', 'Moskovskaya str, 273', '375297823547', 'Mon - Fri 10.00 - 18.00, Sat - Sun 10.00 - 16.00');
insert into shop (name, city, address, phone_number, working_hours)
values('Calypso5', 'Grodno', 'Lenina str, 19', '375336896455', 'Mon - Fri 10.00 - 18.00, Sat - Sun 10.00 - 16.00');

insert into shopping_cart (account_id)
values('1');
insert into shopping_cart (account_id)
values('2');
insert into shopping_cart (account_id)
values('3');
insert into shopping_cart (account_id)
values('4');
insert into shopping_cart (account_id)
values('5');
insert into shopping_cart (account_id)
values('6');

insert into status (name)
values('new');
insert into status (name)
values('paying');
insert into status (name)
values('delivery');
insert into status (name)
values('done');