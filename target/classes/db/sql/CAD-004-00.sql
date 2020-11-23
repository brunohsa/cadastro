--liquibase formatted sql
--changeset bruno:CAD-004-00

ALTER TABLE Cadastro ADD COLUMN categoria VARCHAR(100);
ALTER TABLE Cadastro ADD COLUMN nota NUMERIC;