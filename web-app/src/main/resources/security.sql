drop table if exists users;
drop table if exists roles;
drop table if exists users_roles;


CREATE TABLE users (
  id bigint NOT NULL AUTO_INCREMENT,
  login varchar(50) NOT NULL,
  password varchar(100) NOT NULL,
  account_id bigint NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE roles (
  id bigint NOT NULL AUTO_INCREMENT,
  role_name varchar(50) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE users_roles (
  user_id bigint NOT NULL,
  role_id bigint NOT NULL,
  PRIMARY KEY (user_id, role_id),
  CONSTRAINT role_fk FOREIGN KEY (user_id) REFERENCES users (id),
  CONSTRAINT user_fk FOREIGN KEY (role_id) REFERENCES roles (id)
);

INSERT INTO users (login, password, account_id)
VALUES ('Julia', '$2a$10$qohY5NgZ1NPll40UmgQFiO3DdyvX6iwjRd1YETvTAl0dbcU6R16nm', 1);
INSERT INTO users (login, password, account_id)
VALUES ('Oksana', '$2a$10$AHeINJUzXyb3oBY5adrFy.PeuvIZZbjdav4X1qAIY63oFrL.F/L7W', 2);
INSERT INTO users (login, password, account_id)
VALUES ('Anton', '$2a$10$shEO2.MUoe1/GkvjIyzeTOzydBRV9uksJym0Oe08gP2JUVmBPmiGO', 3);
INSERT INTO users (login, password, account_id)
VALUES ('Vladimir', '$2a$10$AWVlz4/UV7WMLrazlFdfLOVZQbPkShrW86RG3pYiORZog4faJ3tda', 4);
INSERT INTO users (login, password, account_id)
VALUES ('Maksim', '$2a$10$tN29LlaHTq.clo5O/SchiefUDDdcBq5z21uPfDBhNrTDLqUUFjsJS', 5);
INSERT INTO users (login, password, account_id)
VALUES ('Sergey', '$2a$10$v1x4K2AgvaSMPQAgOSu.zesDPq8oRuAFlBtkFI1nD7VO/dsrJeufO', 6);
INSERT INTO users (login, password, account_id)
VALUES ('Askona', '$2a$10$EZYS/DE.dJuomPOqSSScoOS4bcIs7r1IL7etzfVPA5Xgz5E6rkCwa', 7);
INSERT INTO users (login, password, account_id)
VALUES ('Solo', '$2a$10$yN0LDhY7x.eRYxAi2zvxsuxyxMZpF9JFVtY77wV3Be2Sn4SqEJAmu', 8);
INSERT INTO users (login, password, account_id)
VALUES ('Prima', '$2a$10$Qb.TThP7XhQFoj4UyEFZAed8StjMy0eRx1.gzJvSiv/n65huxOqGS', 9);

INSERT INTO roles (role_name) VALUES ('CUSTOMER');
INSERT INTO roles (role_name) VALUES ('SELLER');

INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (3, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (4, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (5, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (6, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (7, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (8, 2);