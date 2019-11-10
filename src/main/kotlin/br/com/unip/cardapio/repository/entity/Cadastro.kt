package br.com.unip.cardapio.repository.entity

import br.com.unip.cardapio.repository.entity.enums.EStatusCadastro
import br.com.unip.cardapio.repository.util.PersistenciaUtil
import org.hibernate.annotations.Cascade
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.PersistenceUtil

@Entity
class Cadastro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private val pessoa: Pessoa

    @Column
    @Enumerated(EnumType.STRING)
    var status: EStatusCadastro

    @Column
    val uuid: String = UUID.randomUUID().toString()

    constructor(pessoa: Pessoa, status: EStatusCadastro) {
        this.pessoa = pessoa
        this.status = status
    }

    fun getPessoa(): Pessoa {
        return PersistenciaUtil.inicializarERemoverProxy(pessoa)
    }
}