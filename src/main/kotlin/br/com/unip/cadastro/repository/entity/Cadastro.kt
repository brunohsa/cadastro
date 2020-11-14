package br.com.unip.cadastro.repository.entity

import br.com.unip.cadastro.repository.entity.enums.EStatusCadastro
import br.com.unip.cadastro.repository.util.PersistenciaUtil
import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType.PERSIST
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
class Cadastro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @OneToOne(mappedBy = "cadastro")
    private lateinit var pessoa: Pessoa

    @Column
    @Enumerated(EnumType.STRING)
    var status: EStatusCadastro

    @Column
    val uuid: String = UUID.randomUUID().toString()

    var urlImagem: String? = ""

    constructor(status: EStatusCadastro) {
        this.status = status
    }

    fun getPessoa(): Pessoa {
        return PersistenciaUtil.inicializarERemoverProxy(pessoa)
    }
}