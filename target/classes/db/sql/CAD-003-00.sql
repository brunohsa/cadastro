--liquibase formatted sql
--changeset bruno:CAD-003-00

ALTER TABLE Cadastro ADD column urlImagem varchar(500);