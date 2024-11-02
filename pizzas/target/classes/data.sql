-- -- Tworzenie tabeli Ingredient (składniki)
create table if not exists Ingredient (
                                          id varchar(4) not null,
                                          name varchar(35) not null,
                                          type varchar(10) not null,
                                          primary key (id)
);

-- Dodanie składników do tabeli Ingredient
insert into Ingredient (id, name, type) values
                                            ('GRB', 'Na grubym', 'DOUGH'),
                                            ('CNK', 'Na cienkim', 'DOUGH'),
                                            ('WOł', 'Mielona wołowina', 'MEAT'),
                                            ('KUR', 'Szarpany kurczak', 'MEAT'),
                                            ('SAL', 'Salami', 'MEAT'),
                                            ('PAP', 'Papryka słodka', 'VEGETABLES'),
                                            ('JAL', 'Papryka jalapeno', 'VEGETABLES'),
                                            ('OGK', 'Ogórek kiszony', 'VEGETABLES'),
                                            ('PIE', 'Pieczarki', 'VEGETABLES'),
                                            ('CZO', 'Czosnkowy', 'SAUCE'),
                                            ('OST', 'Ostry', 'SAUCE');

CREATE TABLE IF NOT EXISTS users (
                                     id BIGINT NOT NULL AUTO_INCREMENT,
                                     username VARCHAR(255) NOT NULL UNIQUE,
                                     password VARCHAR(255) NOT NULL,
                                     full_name VARCHAR(255) NOT NULL,
                                     street VARCHAR(255),
                                     city VARCHAR(255),
                                     zip VARCHAR(10),
                                     phone_number VARCHAR(20),
                                     PRIMARY KEY (id));

INSERT INTO users (id, username, password, full_name, city, street, zip, phone_number)
VALUES
    (0, 'sa', '$2a$12$U45TiH5COTD4jYIfL1i8E.uJoXG5ZL8.6J2gXjUOG8W5gJXlgC1Ja', 'Test User', 'Test City', 'Test Street', '12345', '123456789');

