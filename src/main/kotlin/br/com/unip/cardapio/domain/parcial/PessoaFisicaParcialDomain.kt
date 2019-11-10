package br.com.unip.cardapio.domain.parcial

import br.com.unip.cardapio.domain.campos.Nome
import br.com.unip.cardapio.domain.campos.Sobrenome
import br.com.unip.cardapio.domain.campos.Telefone
import br.com.unip.cardapio.domain.campos.parcial.CPFParcial
import br.com.unip.cardapio.domain.campos.parcial.DataNascimentoParcial

class PessoaFisicaParcialDomain : IPessoaParcialDomain {

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

    override fun dadosObrigatoriosPreenchidos(): Boolean {
        return nome.get().isNullOrEmpty() || cpf.get().isNullOrEmpty() || dataNascimento.get() == null
    }
}