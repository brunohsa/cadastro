package br.com.unip.cadastro.domain

import br.com.unip.cadastro.domain.campos.pessoa.pj.CNPJ
import br.com.unip.cadastro.domain.campos.pessoa.pf.Nome
import br.com.unip.cadastro.domain.campos.pessoa.Telefone
import br.com.unip.cadastro.domain.campos.pessoa.pj.NomeFantasia
import br.com.unip.cadastro.domain.campos.pessoa.pj.RazaoSocial

class PessoaJuridicaDomain : IPessoaDomain {

    val razaoSocial: RazaoSocial
    val nomeFantasia: NomeFantasia
    val telefone: Telefone
    val cnpj: CNPJ

    constructor(razaoSocial: String?, nomeFantasia: String?, telefone: String?, cnpj: String?) {
        this.razaoSocial = RazaoSocial(razaoSocial)
        this.nomeFantasia = NomeFantasia(nomeFantasia)
        this.telefone = Telefone(telefone)
        this.cnpj = CNPJ(cnpj)
    }
}