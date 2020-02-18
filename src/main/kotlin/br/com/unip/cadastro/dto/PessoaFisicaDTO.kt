package br.com.unip.cadastro.dto

class PessoaFisicaDTO : IPessoaDTO {

    var nome: String? = ""
    val sobrenome: String?
    val telefone: String?
    val dataNascimento: String?
    val cpf: String?

    constructor(nome: String?, sobrenome: String?, telefone: String?, dataNascimento: String?, cpf: String?)
            : this(sobrenome, telefone, dataNascimento, cpf) {
        this.nome = nome
    }

    constructor(sobrenome: String?, telefone: String?, dataNascimento: String?, cpf: String?) {
        this.sobrenome = sobrenome
        this.telefone = telefone
        this.dataNascimento = dataNascimento
        this.cpf = cpf
    }
}

