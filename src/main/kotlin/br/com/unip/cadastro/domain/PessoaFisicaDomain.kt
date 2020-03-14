package br.com.unip.cadastro.domain

import br.com.unip.cadastro.domain.campos.pessoa.Telefone
import br.com.unip.cadastro.domain.campos.pessoa.pf.CPF
import br.com.unip.cadastro.domain.campos.pessoa.pf.Nome
import br.com.unip.cadastro.domain.campos.pessoa.pf.Sobrenome

class PessoaFisicaDomain : IPessoaDomain {

    val nome: Nome
    val sobrenome: Sobrenome
    val telefone: Telefone
    val cpf: CPF

    constructor(nome: String?, sobrenome: String?, telefone: String?, cpf: String?) {
        this.nome = Nome(nome)
        this.sobrenome = Sobrenome(sobrenome)
        this.telefone = Telefone(telefone)
        this.cpf = CPF(cpf)
    }
}