package br.com.unip.cardapio.domain.parcial

import br.com.unip.cardapio.domain.campos.Nome
import br.com.unip.cardapio.domain.campos.Telefone
import br.com.unip.cardapio.domain.campos.parcial.CNPJParcial
import br.com.unip.cardapio.domain.campos.parcial.DataFundacaoParcial

class PessoaJuridicaParcialDomain : IPessoaParcialDomain {

    val nome: Nome
    val telefone: Telefone
    val dataFundacao: DataFundacaoParcial
    val cnpj: CNPJParcial

    constructor(nome: String?, telefone: String?, dataFundacao: String?, cnpj: String?) {
        this.nome = Nome(nome)
        this.telefone = Telefone(telefone)
        this.dataFundacao = DataFundacaoParcial(dataFundacao)
        this.cnpj = CNPJParcial(cnpj)
    }

    override fun dadosObrigatoriosPreenchidos(): Boolean {
        return nome.get().isNullOrEmpty() || cnpj.get().isNullOrEmpty()
    }
}