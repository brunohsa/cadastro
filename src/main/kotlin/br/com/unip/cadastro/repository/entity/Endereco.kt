package br.com.unip.cadastro.repository.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
open class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column
    var codigoPostal: String

    @Column
    val bairro: String

    @Column
    var cidade: String

    @Column
    var estado: String

    @Column
    var logradouro: String

    @Column
    var numero: String

    @Column
    var latitude: Double

    @Column
    var longitude: Double

    constructor(cep: String, bairro: String, cidade: String, estado: String, logradouro: String,
                numero: String, latitude: Double, longitude: Double) {
        this.codigoPostal = cep
        this.bairro = bairro
        this.cidade = cidade
        this.estado = estado
        this.logradouro = logradouro
        this.numero = numero
        this.latitude = latitude
        this.longitude = longitude
    }
}