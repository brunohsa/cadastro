package br.com.unip.cardapio.dto

import br.com.unip.cardapio.repository.entity.enums.ETipoDocumento
import javax.persistence.Column

class PessoaDTO {

    val nome: String

    var tipoDocumento: String

    var numero: String

    constructor(nome: String, tipoDocumento: String, numero: String) {
        this.nome = nome
        this.tipoDocumento = tipoDocumento
        this.numero = numero
    }
}

