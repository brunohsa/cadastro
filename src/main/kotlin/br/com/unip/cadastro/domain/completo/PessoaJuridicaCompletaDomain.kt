package br.com.unip.cadastro.domain.completo

import br.com.unip.cadastro.domain.campos.pessoa.Nome
import br.com.unip.cadastro.domain.campos.pessoa.Telefone
import br.com.unip.cadastro.domain.campos.pessoa.completo.CNPJCompleto

class PessoaJuridicaCompletaDomain : IPessoaCompletaDomain {

    val razaoSocial: Nome
    val nomeFantasia: Nome
    val telefone: Telefone
    val cnpj: CNPJCompleto

    constructor(razaoSocial: String?, nomeFantasia: String?, telefone: String?, cnpj: String?) {
        this.razaoSocial = Nome(razaoSocial)
        this.nomeFantasia = Nome(nomeFantasia)
        this.telefone = Telefone(telefone)
        this.cnpj = CNPJCompleto(cnpj)
    }
}