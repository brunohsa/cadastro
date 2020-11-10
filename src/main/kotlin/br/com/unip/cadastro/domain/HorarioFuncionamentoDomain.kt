package br.com.unip.cadastro.domain

import br.com.unip.cadastro.domain.campos.horarios.horariofunc.DiaSemana
import br.com.unip.cadastro.domain.campos.horarios.horariofunc.Fechado

class HorarioFuncionamentoDomain {

    val diaSemana: DiaSemana

    val funcionamento: FuncionamentoDomain

    val fechado: Fechado

    constructor(diaSemana: String?, abertura: String?, fechamento: String?, fechado: Boolean?) {
        this.diaSemana = DiaSemana(diaSemana)
        this.funcionamento = FuncionamentoDomain(abertura, fechamento)
        this.fechado = Fechado(fechado)
    }
}