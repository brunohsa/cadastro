package br.com.unip.cardapio.domain.completo

import br.com.unip.cardapio.domain.campos.Sobrenome
import br.com.unip.cardapio.domain.campos.Telefone
import br.com.unip.cardapio.domain.campos.completo.CPFCompleto
import br.com.unip.cardapio.domain.campos.completo.DataNascimentoCompleto

class PessoaFisicaCompletaDomain : IPessoaCompletaDomain {

    val sobrenome: Sobrenome
    val telefone: Telefone
    val dataNascimento: DataNascimentoCompleto
    val cpf: CPFCompleto

    constructor(sobrenome: String?, telefone: String?, dataNascimento: String?, cpf: String?) {
        this.sobrenome = Sobrenome(sobrenome)
        this.telefone = Telefone(telefone)
        this.dataNascimento = DataNascimentoCompleto(dataNascimento)
        this.cpf = CPFCompleto(cpf)
    }
}