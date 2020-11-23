package br.com.unip.cadastro.repository.entity

import br.com.unip.cadastro.repository.util.PersistenciaUtil
import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType.ALL
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
    @Cascade(ALL)
    @JoinColumn(name = "documento_id")
    private var documento: Documento? = null

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @Cascade(ALL)
    @JoinColumn(name = "endereco_id")
    private var endereco: Endereco? = null

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @Cascade(ALL)
    @JoinColumn(name = "cadastro_id")
    private lateinit var cadastro: Cadastro

    constructor(nome: String?, telefone: String?, documento: Documento?) {
        this.nome = nome
        this.telefone = telefone
        this.documento = documento
    }

    fun getDocumento(): Documento? {
        if (documento == null) {
            return null
        }
        return PersistenciaUtil.inicializarERemoverProxy(documento)
    }

    fun getEndereco(): Endereco? {
        return PersistenciaUtil.inicializarERemoverProxy(endereco)
    }

    fun getCadastro(): Cadastro {
        return PersistenciaUtil.inicializarERemoverProxy(cadastro)
    }

    fun adicionarEndereco(endereco: Endereco) {
        this.endereco = endereco
    }

    fun adicionarCadastro(cadastro: Cadastro) {
        this.cadastro = cadastro
    }

    fun adicionarDocumento(documento: Documento) {
        this.documento = documento
    }
}