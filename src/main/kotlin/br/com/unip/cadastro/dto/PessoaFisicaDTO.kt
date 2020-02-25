package br.com.unip.cadastro.dto

class PessoaFisicaDTO : IPessoaDTO {

    var nome: String? = ""
    val sobrenome: String?
    val telefone: String?
    var dataNascimento: String? = "" //TODO REMOVER
    var documento: DocumentoDTO? = null

    constructor(nome: String?, sobrenome: String?, telefone: String?, dataNascimento: String?, documento: DocumentoDTO?)
            : this(sobrenome, telefone, dataNascimento, documento) {
        this.nome = nome
    }

    constructor(sobrenome: String?, telefone: String?, dataNascimento: String?, documento: DocumentoDTO?) {
        this.sobrenome = sobrenome
        this.telefone = telefone
        this.dataNascimento = dataNascimento
        this.documento = documento
    }

    constructor(nome: String?, sobrenome: String?, telefone: String?) {
        this.nome = nome
        this.sobrenome = sobrenome
        this.telefone = telefone
    }
}

