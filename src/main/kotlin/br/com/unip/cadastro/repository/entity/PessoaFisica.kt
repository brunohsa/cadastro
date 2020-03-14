package br.com.unip.cadastro.repository.entity

import javax.persistence.Column
import javax.persistence.Entity

@Entity
class PessoaFisica(nome: String,
                   @Column var sobrenome: String?,
                   telefone: String?,
                   documento: Documento?) : Pessoa(nome, telefone, documento)