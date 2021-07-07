--DROP TABLE IF EXISTS car;
--DROP TABLE IF EXISTS vendor;
--DROP TABLE IF EXISTS customer;
--DROP TABLE IF EXISTS invoice;

CREATE TABLE IF NOT EXISTS car (
    id serial PRIMARY KEY,
    brand VARCHAR(255),
    model VARCHAR(255),
    type VARCHAR(255),
    yearManuf INTEGER,
    yearModel INTEGER,
    price REAL,
    quantity INTEGER
);

CREATE TABLE IF NOT EXISTS invoice (
    id serial PRIMARY KEY,
    listCars VARCHAR(255),
    totalPrice REAL,
    paymentMethod VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS customer (
    id serial PRIMARY KEY,
    name VARCHAR(255),
    cpf VARCHAR(9),
    email VARCHAR(255),
    phone VARCHAR(255),
    carsCustomer VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS vendor (
    id serial PRIMARY KEY,
    name VARCHAR(255),
    registration VARCHAR(6),
    cpf VARCHAR(9),
    email VARCHAR(255)
);