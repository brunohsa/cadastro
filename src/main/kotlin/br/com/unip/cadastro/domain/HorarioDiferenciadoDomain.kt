package br.com.unip.cadastro.domain

import br.com.unip.cadastro.domain.campos.horariodif.Abertura
import br.com.unip.cadastro.domain.campos.horariodif.DataEspecial
import br.com.unip.cadastro.domain.campos.horariodif.Fechamento

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