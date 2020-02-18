package br.com.unip.cadastro.repository.entity

import br.com.unip.cadastro.repository.util.PersistenciaUtil
import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType.PERSIST
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Inheritance
import javax.persistence.InheritanceType
import javax.persistence.JoinColumn
import javax.persistence.OneToOne

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
open class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column
    var nome: String?

    @Column
    var telefone: String?

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @Cascade(PERSIST)
    @JoinColumn(name = "documento_id")
    private val documento: Documento

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @Cascade(PERSIST)
    @JoinColumn(name = "endereco_id")
    private var endereco: Endereco?

    constructor(nome: String?, telefone: String?, documento: Documento, endereco: Endereco?) {
        this.nome = nome
        this.telefone = telefone
        this.documento = documento
        this.endereco = endereco
    }

    fun getDocumento(): Documento {
        return PersistenciaUtil.inicializarERemoverProxy(documento)
    }

    fun getEndereco(): Endereco? {
        return PersistenciaUtil.inicializarERemoverProxy(endereco)
    }

    fun adicionarEndereco(endereco: Endereco) {
        this.endereco = endereco
    }
}