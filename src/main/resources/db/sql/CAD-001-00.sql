--liquibase formatted sql
--changeset bruno:CAD-001-00

CREATE TABLE Documento (
    id int NOT NULL AUTO_INCREMENT,
    numero varchar(50) NOT NULL,
    tipoDocumento varchar(10) NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE Endereco (
    id int NOT NULL AUTO_INCREMENT,
    codigoPostal varchar(8) NOT NULL,
    bairro varchar(255),
    cidade varchar(255),
    estado varchar(255),
    logradouro varchar(255),
    numero varchar(5) NOT NULL,
    latitude float(10, 6) NOT NULL,
    longitude float(10, 6) NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE Cadastro (
    id int NOT NULL AUTO_INCREMENT,
    status varchar(11) NOT NULL,
    uuid varchar(40) NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE Pessoa (
    id int NOT NULL AUTO_INCREMENT,
    nome varchar(100) NOT NULL,
    telefone varchar(11),
    documento_id int,
    endereco_id int,
    cadastro_id int,

    PRIMARY KEY (id)
);

ALTER TABLE Pessoa ADD CONSTRAINT fk_documento FOREIGN KEY (documento_id) REFERENCES Documento (id);
ALTER TABLE Pessoa ADD CONSTRAINT fk_endereco FOREIGN KEY (endereco_id) REFERENCES Endereco (id);
ALTER TABLE Pessoa ADD CONSTRAINT fk_cadastro FOREIGN KEY (cadastro_id) REFERENCES Cadastro (id);

CREATE TABLE PessoaFisica (
    id int NOT NULL,
    sobrenome varchar(50),
    dataNascimento date
);

CREATE TABLE PessoaJuridica (
    id int NOT NULL,
    nomeFantasia varchar(50)
);