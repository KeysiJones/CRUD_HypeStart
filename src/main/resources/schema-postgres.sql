--DROP TABLE IF EXISTS carro;
CREATE TABLE IF NOT EXISTS carro (
    id serial PRIMARY KEY,
    marca VARCHAR(255),
    modelo VARCHAR(255),
    tipo VARCHAR(255),
    preco REAL,
    quantidade INTEGER
);
