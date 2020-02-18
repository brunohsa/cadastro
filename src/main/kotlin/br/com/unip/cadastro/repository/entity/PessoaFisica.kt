package br.com.unip.cadastro.repository.entity

import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity

@Entity
class PessoaFisica(nome: String,
                   @Column var sobrenome: String?,
                   telefone: String?,
                   @Column var dataNascimento: LocalDate?,
                   documento: Documento,
                   endereco: Endereco?) : Pessoa(nome, telefone, documento, endereco)