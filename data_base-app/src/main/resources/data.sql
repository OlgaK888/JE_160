drop table if exists account CASCADE;
drop table if exists category CASCADE;
drop table if exists category_products CASCADE;
drop table if exists comment CASCADE;
drop table if exists delivery CASCADE;
drop table if exists payment_type CASCADE;
drop table if exists payment_type_deliveries CASCADE;
drop table if exists product CASCADE;
drop table if exists purchase_order CASCADE;
drop table if exists review CASCADE;
drop table if exists shop CASCADE;
drop table if exists shopping_cart_product CASCADE;
drop table if exists shopping_cart CASCADE;
drop table if exists status CASCADE;

create table account (
    id bigint generated by default as identity,
    name varchar(255),
    address varchar(255),
    city varchar(255),
    country varchar(255),
    email varchar(255),
    phone_number bigint not null,
    postal_code integer not null,
    shopping_cart_id bigint,
    primary key (id));

create table category (
    id bigint generated by default as identity,
    name varchar(255),
    primary key (id));

create table comment (
    id bigint generated by default as identity,
    account_id bigint not null,
    message varchar(255),
    product_id bigint,
    primary key (id));

create table review (
    id bigint generated by default as identity,
    account_id bigint not null,
    message varchar(255),
    product_id bigint,
    primary key (id));

create table payment_type (
    id bigint generated by default as identity,
    type_name varchar(255),
    primary key (id));

create table delivery (
    id bigint generated by default as identity,
    delivery_time timestamp,
    payment_type_id bigint,
    purchase_order_id bigint,
    shop_id bigint,
    primary key (id));

create table payment_type_deliveries (
    payment_type_id bigint not null,
    deliveries_id bigint not null);

create table product (
    id bigint generated by default as identity,
    name varchar(255),
    description varchar(255),
    price decimal(19,2),
    rating double not null,
    account_seller_id bigint not null,
    category_id bigint,
    comment_id bigint,
    review_id bigint,
    primary key (id));

create table category_products (
    category_id bigint not null,
    products_id bigint not null);

create table purchase_order (
    id bigint generated by default as identity,
    order_time timestamp,
    status varchar(255),
    sum decimal(19,2),
    shopping_cart_id bigint,
    delivery_id bigint,
    primary key (id));

create table shop (
    id bigint generated by default as identity,
    name varchar(255),
    city varchar(255),
    address varchar(255),
    delivery_id bigint,
    primary key (id));

create table shopping_cart (
    id bigint generated by default as identity,
    account_id bigint,
    purchase_order_id bigint,
    primary key (id));

create table shopping_cart_product (
    shopping_cart_id bigint not null,
    product_id bigint not null);

create table status (
    id bigint generated by default as identity,
    name varchar(255),
    purchase_order_id bigint,
    primary key (id));

alter table category_products add unique (products_id);
alter table payment_type_deliveries add unique (deliveries_id);
alter table account add foreign key (shopping_cart_id) references shopping_cart;
alter table category_products add foreign key (products_id) references product;
alter table category_products add foreign key (category_id) references category;
alter table comment add foreign key (product_id) references product;
alter table delivery add foreign key (payment_type_id) references payment_type;
alter table delivery add foreign key (purchase_order_id) references purchase_order;
alter table delivery add foreign key (shop_id) references shop;
alter table payment_type_deliveries add foreign key (deliveries_id) references delivery;
alter table payment_type_deliveries add foreign key (payment_type_id) references payment_type;
alter table product add foreign key (category_id) references category;
alter table product add foreign key (comment_id) references comment;
alter table product add foreign key (review_id) references review;
alter table purchase_order add foreign key (delivery_id) references delivery;
alter table purchase_order add foreign key (shopping_cart_id) references shopping_cart;
alter table review add foreign key (product_id) references product;
alter table shop add foreign key (delivery_id) references delivery;
alter table shopping_cart_product add foreign key (product_id) references product;
alter table shopping_cart_product add foreign key (shopping_cart_id) references shopping_cart;
alter table shopping_cart add foreign key (account_id) references account;
alter table shopping_cart add foreign key (purchase_order_id) references purchase_order;
alter table status add foreign key (purchase_order_id) references purchase_order;


insert into account (name, address, city, country, email, phone_number, postal_code)
values('Elisaveta Petrova', 'Moskovskaya str, 266, 15', 'Brest', 'Belarus', 'elisaveta@mail.ru' , '375295768493', '224008');
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

insert into product (name, description, price, rating, account_seller_id, comment_id, review_id)
values('sofa Amatti', 'Big sofa 3200*1000*1000', '4900', '4.8', '1', '5', '5');
insert into product (name, description, price, rating, account_seller_id, comment_id, review_id)
values('sofa Altera', 'Standard classic sofa 2200*1000*1000', '3200', '4.5', '1', '3', '3');
insert into product (name, description, price, rating, account_seller_id, comment_id, review_id)
values('table Classic', 'Classic table 1500*850*750', '2000', '4.4', '2', '1', '1');
insert into product (name, description, price, rating, account_seller_id, comment_id, review_id)
values('table Optima', 'Compact table 1200*750*750', '1400', '4.9', '2', '2', '2');
insert into product (name, description, price, rating, account_seller_id, comment_id, review_id)
values('armchair Credo', 'Classic armchair 900*950*800', '1500', '4.2', '1', '6', '6');
insert into product (name, description, price, rating, account_seller_id, comment_id, review_id)
values('armchair Modum', 'Modern armchair 1000*850*900', '1600', '4.6', '1', '4', '4');
insert into product (name, description, price, rating, account_seller_id, comment_id, review_id)
values('chair Condor', 'Classic chair 500*600*1000', '900', '4.1', '2', '5', '5');
insert into product (name, description, price, rating, account_seller_id, comment_id, review_id)
values('chair Vinotti', 'Modern chair 500*600*900', '1000', '4.0', '2', '3', '3');
insert into product (name, description, price, rating, account_seller_id, comment_id, review_id)
values('bed Lumia', 'Classic bed 1960*2100', '1300', '4.4', '3', '1', '1');
insert into product (name, description, price, rating, account_seller_id, comment_id, review_id)
values('bed Edem', 'Modern bed 1700*2100', '1400', '4.6', '3', '5', '5');

insert into category_products (category_id, products_id)
values('1', '1');
insert into category_products (category_id, products_id)
values('1', '2');
insert into category_products (category_id, products_id)
values('2', '3');
insert into category_products (category_id, products_id)
values('2', '4');
insert into category_products (category_id, products_id)
values('3', '5');
insert into category_products (category_id, products_id)
values('3', '6');
insert into category_products (category_id, products_id)
values('4', '7');
insert into category_products (category_id, products_id)
values('4', '8');
insert into category_products (category_id, products_id)
values('5', '9');
insert into category_products (category_id, products_id)
values('5', '10');

insert into purchase_order (order_time, status, sum, delivery_id)
values('2021-10-15', '1', '4900', '1');
insert into purchase_order (order_time, status, sum, delivery_id)
values('2021-10-20', '1', '3200', '2');
insert into purchase_order (order_time, status, sum, delivery_id)
values('2021-10-16', '1', '1500', '3');
insert into purchase_order (order_time, status, sum, delivery_id)
values('2021-10-12', '1', '900', '4');

insert into shop (name, city, address)
values('Calypso1', 'Minsk', 'Dzerginskogo str, 205');
insert into shop (name, city, address)
values('Calypso2', 'Minsk', 'Yakuba Kolasa str, 53');
insert into shop (name, city, address)
values('Calypso3', 'Minsk', 'Esenina str, 56');
insert into shop (name, city, address)
values('Calypso4', 'Brest', 'Moskovskaya str, 273');
insert into shop (name, city, address)
values('Calypso5', 'Grodno', 'Lenina str, 19');

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