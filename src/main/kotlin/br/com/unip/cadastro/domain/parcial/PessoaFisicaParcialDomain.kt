package br.com.unip.cadastro.domain.parcial

import br.com.unip.cadastro.domain.IPessoaDomain
import br.com.unip.cadastro.domain.campos.pessoa.Nome
import br.com.unip.cadastro.domain.campos.pessoa.Sobrenome
import br.com.unip.cadastro.domain.campos.pessoa.Telefone
import br.com.unip.cadastro.domain.campos.pessoa.parcial.CPFParcial
import br.com.unip.cadastro.domain.campos.pessoa.parcial.DataNascimentoParcial

class PessoaFisicaParcialDomain : IPessoaDomain {

    val nome: Nome
    val sobrenome: Sobrenome
    val telefone: Telefone
    val dataNascimento: DataNascimentoParcial
    val cpf: CPFParcial

    constructor(nome: String?, sobrenome: String?, telefone: String?, dataNascimento: String?, cpf: String?) {
        this.nome = Nome(nome)
        this.sobrenome = Sobrenome(sobrenome)
        this.telefone = Telefone(telefone)
        this.dataNascimento = DataNascimentoParcial(dataNascimento)
        this.cpf = CPFParcial(cpf)
    }
}