package br.com.unip.cadastro.webservice.model.response.erro

import com.fasterxml.jackson.annotation.JsonProperty

class Erro {

    @JsonProperty(value = "codigo")
    lateinit var codigoErro: String

    @JsonProperty(value = "mensagem")
    var mensagem: String? = ""

    @JsonProperty(value = "microservico")
    var microservice: EMicroservice = EMicroservice.CADASTRO

    constructor()

    constructor(codigo: String, mensagem: String?) {
        this.codigoErro = codigo
        this.mensagem = mensagem
    }
}