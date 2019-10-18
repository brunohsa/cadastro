package br.com.unip.cardapio.domain

import br.com.unip.cardapio.domain.campos.CPF
import br.com.unip.cardapio.domain.campos.DataPassada
import br.com.unip.cardapio.domain.campos.Nome
import br.com.unip.cardapio.domain.campos.Sobrenome
import br.com.unip.cardapio.domain.campos.Telefone

class PessoaFisicaDomain {

    val nome: Nome
    val sobrenome: Sobrenome
    val telefone: Telefone
    val dataNascimento: DataPassada
    val cpf: CPF

    constructor(nome: String, sobrenome: String, telefone: String, dataNascimento: String, cpf: String) {
        this.nome = Nome(nome)
        this.sobrenome = Sobrenome(sobrenome)
        this.telefone = Telefone(telefone)
        this.dataNascimento = DataPassada(dataNascimento)
        this.cpf = CPF(cpf)
    }
}