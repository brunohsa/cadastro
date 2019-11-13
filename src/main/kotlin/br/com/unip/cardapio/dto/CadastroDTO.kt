package br.com.unip.cardapio.dto

class CadastroDTO {

    val uuid: String

    val status: String

    val pessoa: PessoaDTO

    constructor(uuid: String, status: String, pessoa: PessoaDTO) {
        this.uuid = uuid
        this.status = status
        this.pessoa = pessoa
    }
}