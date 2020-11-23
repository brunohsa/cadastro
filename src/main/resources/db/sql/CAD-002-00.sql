--liquibase formatted sql
--changeset bruno:CAD-002-00

CREATE TABLE HorarioFuncionamento (
    id int NOT NULL AUTO_INCREMENT,
    diaSemana varchar(20) NOT NULL,
    abertura varchar(5),
    fechamento varchar(5),
    fechado TINYINT(1),
    cadastro_id int,

    PRIMARY KEY (id)
);

ALTER TABLE HorarioFuncionamento ADD CONSTRAINT fk_cadastro_horario_func FOREIGN KEY (cadastro_id) REFERENCES Cadastro (id);

CREATE TABLE HorarioDiferenciado (
    id int NOT NULL AUTO_INCREMENT,
    dataCadastro date,
    dataEspecial date,
    abertura varchar(5),
    fechamento varchar(5),
    cadastro_id int,

    PRIMARY KEY (id)
);

ALTER TABLE HorarioDiferenciado ADD CONSTRAINT fk_cadastro_horario_dif FOREIGN KEY (cadastro_id) REFERENCES Cadastro (id);

