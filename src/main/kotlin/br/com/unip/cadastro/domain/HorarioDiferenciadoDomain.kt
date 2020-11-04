package br.com.unip.cadastro.domain

import br.com.unip.cadastro.domain.campos.horarios.Abertura
import br.com.unip.cadastro.domain.campos.horarios.horariodif.DataEspecial
import br.com.unip.cadastro.domain.campos.horarios.Fechamento

class HorarioDiferenciadoDomain {

    val dataEspecial: DataEspecial

    val abertura: Abertura

    val fechamento: Fechamento

    constructor(dataEspecial: String?, abertura: String?, fechamento: String?) {
        this.dataEspecial = DataEspecial(dataEspecial)
        this.abertura = Abertura(abertura)
        this.fechamento = Fechamento(fechamento)
    }
}