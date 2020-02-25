package br.com.unip.cadastro.domain

import br.com.unip.cadastro.domain.campos.pessoa.pf.CPF
import br.com.unip.cadastro.domain.campos.pessoa.pf.DataNascimento
import br.com.unip.cadastro.domain.campos.pessoa.pf.Nome
import br.com.unip.cadastro.domain.campos.pessoa.pf.Sobrenome
import br.com.unip.cadastro.domain.campos.pessoa.Telefone

class PessoaFisicaDomain : IPessoaDomain {

    val nome: Nome
    val sobrenome: Sobrenome
    val telefone: Telefone
    val dataNascimento: DataNascimento
    val cpf: CPF

    constructor(nome: String?, sobrenome: String?, telefone: String?, dataNascimento: String?, cpf: String?) {
        this.nome = Nome(nome)
        this.sobrenome = Sobrenome(sobrenome)
        this.telefone = Telefone(telefone)
        this.dataNascimento = DataNascimento(dataNascimento)
        this.cpf = CPF(cpf)
    }
}