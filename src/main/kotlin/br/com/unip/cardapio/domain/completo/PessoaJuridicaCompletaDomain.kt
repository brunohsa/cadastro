package br.com.unip.cardapio.domain.completo

import br.com.unip.cardapio.domain.campos.Telefone
import br.com.unip.cardapio.domain.campos.completo.CNPJCompleto
import br.com.unip.cardapio.domain.campos.completo.DataFundacaoCompleto

class PessoaJuridicaCompletaDomain : IPessoaCompletaDomain {

    val telefone: Telefone
    val dataFundacao: DataFundacaoCompleto
    val cnpj: CNPJCompleto

    constructor(telefone: String?, dataFundacao: String?, cnpj: String?) {
        this.telefone = Telefone(telefone)
        this.dataFundacao = DataFundacaoCompleto(dataFundacao)
        this.cnpj = CNPJCompleto(cnpj)
    }
}