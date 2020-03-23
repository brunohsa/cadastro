package br.com.unip.cadastro.repository.entity

import br.com.unip.cadastro.repository.entity.enums.ETipoDocumento
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
open class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column(unique = true)
    var numero: String

    @Column
    @Enumerated(EnumType.STRING)
    var tipoDocumento: ETipoDocumento
    
    constructor(numero: String, tipoDocumento: ETipoDocumento) {
        this.numero = numero
        this.tipoDocumento = tipoDocumento
    }
}