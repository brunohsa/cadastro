package br.com.unip.cardapio.domain

import br.com.unip.cardapio.domain.campos.CNPJ
import br.com.unip.cardapio.domain.campos.DataPassada
import br.com.unip.cardapio.domain.campos.Nome
import br.com.unip.cardapio.domain.campos.Telefone

class PessoaJuridicaDomain {

    val nome: Nome
    val telefone: Telefone
    val dataFundacao: DataPassada
    val cnpj: CNPJ

    constructor(nome: String, telefone: String, dataFundacao: String, cnpj: String) {
        this.nome = Nome(nome)
        this.telefone = Telefone(telefone)
        this.dataFundacao = DataPassada(dataFundacao)
        this.cnpj = CNPJ(cnpj)
    }
}