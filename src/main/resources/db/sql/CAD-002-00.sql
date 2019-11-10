--liquibase formatted sql
--changeset bruno:CAD-002-00

CREATE TABLE Cadastro (
    id int NOT NULL AUTO_INCREMENT,
    pessoa_id int NOT NULL,
    status varchar(11) NOT NULL,
    uuid varchar(40) NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (pessoa_id) REFERENCES Pessoa(id)
);