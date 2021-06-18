--DROP TABLE IF EXISTS carro;
CREATE TABLE IF NOT EXISTS carro (
    id serial PRIMARY KEY,
    marca VARCHAR(255),
    modelo VARCHAR(255),
    tipo VARCHAR(255),
    preco REAL,
    quantidade INTEGER
);

CREATE TABLE IF NOT EXISTS pedido (
    id serial PRIMARY KEY,
    listaCarros VARCHAR(255),
    valorTotal REAL,
    formaPagamento VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS cliente (
    id serial PRIMARY KEY,
    nome VARCHAR(255),
    cpf VARCHAR(255),
    email VARCHAR(255),
    telefone VARCHAR(255),
    carrosCliente VARCHAR(255)
);