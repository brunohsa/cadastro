package br.com.unip.cadastro.repository.entity

import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
open class HorarioDiferenciado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column
    var dataCadastro: LocalDateTime = LocalDateTime.now()

    @Column
    var dataEspecial: LocalDate

    @Column
    var abertura: String = "00:00"

    @Column
    var fechamento: String = "00:00"

    @ManyToOne
    var cadastro: Cadastro

    constructor(dataEspecial: LocalDate, abertura: String, fechamento: String, cadastro: Cadastro) {
        this.dataEspecial = dataEspecial
        this.abertura = abertura
        this.fechamento = fechamento
        this.cadastro = cadastro
    }
}