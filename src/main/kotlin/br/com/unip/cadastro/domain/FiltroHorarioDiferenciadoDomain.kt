package br.com.unip.cadastro.domain

import br.com.unip.cadastro.domain.campos.Data

class FiltroHorarioDiferenciadoDomain {

    val dataEspecialInicio: Data

    val dataEspecialFim: Data

    val dataCadastro: Data

    constructor(dataCadastro: String?, dataEspecialInicio: String?, dataEspecialFim: String?) {
        this.dataEspecialInicio = Data(dataEspecialInicio)
        this.dataEspecialFim = Data(dataEspecialFim)
        this.dataCadastro = Data(dataCadastro)
    }
}