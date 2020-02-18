package br.com.unip.cadastro.dto

import com.fasterxml.jackson.annotation.JsonProperty

class EnderecoCoordenadasDTO {

    @JsonProperty("cep")
    var cep: String? = ""

    @JsonProperty("bairro")
    var bairro: String? = ""

    @JsonProperty("cidade")
    var cidade: String? = ""

    @JsonProperty("estado")
    var estado: String? = ""

    @JsonProperty("logradouro")
    var logradouro: String? = ""

    @JsonProperty("numero")
    var numero: String? = ""

    @JsonProperty("latitude")
    var latitude: Double = 0.0

    @JsonProperty("longitude")
    var longitude: Double = 0.0

    constructor()

    constructor(cep: String?, bairro: String?, cidade: String?, estado: String?, logradouro: String?,
                numero: String?, latitude: Double, longitude: Double) {
        this.cep = cep
        this.bairro = bairro
        this.cidade = cidade
        this.estado = estado
        this.logradouro = logradouro
        this.numero = numero
        this.latitude = latitude
        this.longitude = longitude
    }
}