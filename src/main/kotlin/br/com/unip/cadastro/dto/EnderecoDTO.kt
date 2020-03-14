package br.com.unip.cadastro.dto

class EnderecoDTO {

    var cep: String?
    var bairro: String?
    var cidade: String?
    var estado: String?
    var logradouro: String?
    var numero: String?
    var coordenadas: CoordenadasDTO? = null

    constructor(cep: String?, bairro: String?, cidade: String?, estado: String?, logradouro: String?, numero: String?,
                coordenadas: CoordenadasDTO) : this(cep, bairro, cidade, estado, logradouro, numero) {
        this.coordenadas = coordenadas
    }

    constructor(cep: String?, bairro: String?, cidade: String?, estado: String?, logradouro: String?, numero: String?) {
        this.cep = cep
        this.bairro = bairro
        this.cidade = cidade
        this.estado = estado
        this.logradouro = logradouro
        this.numero = numero
    }
}