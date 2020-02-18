package br.com.unip.cadastro.domain.completo

import br.com.unip.cadastro.domain.campos.pessoa.Nome
import br.com.unip.cadastro.domain.campos.pessoa.Sobrenome
import br.com.unip.cadastro.domain.campos.pessoa.Telefone
import br.com.unip.cadastro.domain.campos.pessoa.completo.CPFCompleto
import br.com.unip.cadastro.domain.campos.pessoa.completo.DataNascimentoCompleto

class PessoaFisicaCompletaDomain : IPessoaCompletaDomain {

    val nome: Nome
    val sobrenome: Sobrenome
    val telefone: Telefone
    val dataNascimento: DataNascimentoCompleto
    val cpf: CPFCompleto

    constructor(nome: String?, sobrenome: String?, telefone: String?, dataNascimento: String?, cpf: String?) {
        this.nome = Nome(nome)
        this.sobrenome = Sobrenome(sobrenome)
        this.telefone = Telefone(telefone)
        this.dataNascimento = DataNascimentoCompleto(dataNascimento)
        this.cpf = CPFCompleto(cpf)
    }
}