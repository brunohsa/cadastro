package br.com.unip.cardapio.dto

class PessoaJuridicaDTO : IPessoaDTO {

    var nome: String? = ""
    val telefone: String?
    val dataFundacao: String?
    val cnpj: String?

    constructor(telefone: String?, dataFundacao: String?, cnpj: String?) {
        this.telefone = telefone
        this.dataFundacao = dataFundacao
        this.cnpj = cnpj
    }

    constructor(nome: String?, telefone: String?, dataFundacao: String?, cnpj: String?)
            : this(telefone, dataFundacao, cnpj) {
        this.nome = nome
    }
}