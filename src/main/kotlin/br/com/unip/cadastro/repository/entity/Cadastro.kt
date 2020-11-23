package br.com.unip.cadastro.repository.entity

import br.com.unip.cadastro.repository.entity.enums.EStatusCadastro
import br.com.unip.cadastro.repository.util.PersistenciaUtil
import java.util.*
import javax.persistence.*

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

    @Column
    var nota: Double? = null

    @Column
    @Enumerated(EnumType.STRING)
    var categoria: ECategoria? = null

    constructor(status: EStatusCadastro) {
        this.status = status
    }

    fun getPessoa(): Pessoa {
        return PersistenciaUtil.inicializarERemoverProxy(pessoa)
    }
}