package br.com.unip.cadastro.repository.entity

import javax.persistence.Column
import javax.persistence.Entity

@Entity
class PessoaJuridica(nome: String,
                     @Column val nomeFantasia: String?,
                     telefone: String,
                     documento: Documento) : Pessoa(nome, telefone, documento)