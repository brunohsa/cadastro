--liquibase formatted sql
--changeset bruno:CAD-001-00

CREATE TABLE Pessoa (
    id int NOT NULL AUTO_INCREMENT,
    nome varchar(100) NOT NULL,
    telefone varchar(11),
    tipoDocumento varchar(10) NOT NULL,
    numero varchar(30) NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE PessoaFisica (
    id int NOT NULL,
    sobrenome varchar(50),
    dataNascimento date
);

CREATE TABLE PessoaJuridica (
    id int NOT NULL,
    dataFundacao date
);