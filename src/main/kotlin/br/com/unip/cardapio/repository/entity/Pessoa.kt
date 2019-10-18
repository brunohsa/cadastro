package br.com.unip.cardapio.repository.entity

import br.com.unip.cardapio.repository.entity.enums.ETipoDocumento
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Inheritance
import javax.persistence.InheritanceType

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
open class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null

    @Column
    val nome: String

    @Column
    val telefone: String

    @Column
    @Enumerated(EnumType.STRING)
    val tipoDocumento: ETipoDocumento

    @Column(unique = true)
    val numero: String

    @Column
    val uuid: String = UUID.randomUUID().toString()

    constructor(nome: String, telefone: String, tipoDocumento: ETipoDocumento, numero: String) {
        this.nome = nome
        this.telefone = telefone
        this.tipoDocumento = tipoDocumento
        this.numero = numero
    }
}