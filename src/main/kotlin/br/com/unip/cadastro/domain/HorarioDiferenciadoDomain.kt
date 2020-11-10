package br.com.unip.cadastro.domain

import br.com.unip.cadastro.domain.campos.horarios.horariodif.DataEspecial


class HorarioDiferenciadoDomain {

    val dataEspecial: DataEspecial

    val funcionamento: FuncionamentoDomain

    constructor(dataEspecial: String?, abertura: String?, fechamento: String?) {
        this.dataEspecial = DataEspecial(dataEspecial)
        this.funcionamento = FuncionamentoDomain(abertura, fechamento)
    }
}