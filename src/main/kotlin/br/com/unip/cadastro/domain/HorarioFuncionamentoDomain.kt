package br.com.unip.cadastro.domain

import br.com.unip.cadastro.domain.campos.horarios.Abertura
import br.com.unip.cadastro.domain.campos.horarios.Fechamento
import br.com.unip.cadastro.domain.campos.horarios.horariofunc.DiaSemana
import br.com.unip.cadastro.domain.campos.horarios.horariofunc.Fechado

class HorarioFuncionamentoDomain {

    val diaSemana: DiaSemana

    val abertura: Abertura

    val fechamento: Fechamento

    val fechado: Fechado

    constructor(diaSemana: String?, abertura: String?, fechamento: String?, fechado: Boolean?) {
        this.diaSemana = DiaSemana(diaSemana)
        this.abertura = Abertura(abertura)
        this.fechamento = Fechamento(fechamento)
        this.fechado = Fechado(fechado)
    }
}