package br.com.unip.cadastro.dto

class PessoaJuridicaDTO : IPessoaDTO {

    val razaoSocial: String?
    val nomeFantasia: String?
    val telefone: String?
    val cnpj: String?
    var endereco: EnderecoDTO? = null


    constructor(razaoSocial: String?, nomeFantasia: String?, telefone: String?, cnpj: String?) {
        this.razaoSocial = razaoSocial
        this.nomeFantasia = nomeFantasia
        this.telefone = telefone
        this.cnpj = cnpj
    }

    constructor(razaoSocial: String?, nomeFantasia: String?, telefone: String?, cnpj: String?, endereco: EnderecoDTO?)
            : this(razaoSocial, nomeFantasia, telefone, cnpj) {
        this.endereco = endereco
    }
}
