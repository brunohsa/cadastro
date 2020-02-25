package br.com.unip.cadastro.domain

import br.com.unip.cadastro.domain.campos.pessoa.Telefone
import br.com.unip.cadastro.domain.campos.pessoa.pf.CPF
import br.com.unip.cadastro.domain.campos.pessoa.pf.DataNascimento
import br.com.unip.cadastro.domain.campos.pessoa.pf.Sobrenome

class PessoaFisicaAlteradaDomain {

    val sobrenome: Sobrenome
    val telefone: Telefone
    val dataNascimento: DataNascimento
    val cpf: CPF

    constructor(sobrenome: String?, telefone: String?, dataNascimento: String?, cpf: String?) {
        this.sobrenome = Sobrenome(sobrenome)
        this.telefone = Telefone(telefone)
        this.dataNascimento = DataNascimento(dataNascimento)
        this.cpf = CPF(cpf)
    }
}