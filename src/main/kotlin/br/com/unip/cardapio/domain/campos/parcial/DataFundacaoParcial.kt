package br.com.unip.cardapio.domain.campos.parcial

import br.com.unip.cardapio.domain.campos.DataPassada
import br.com.unip.cardapio.domain.campos.ICampo
import java.time.LocalDate

class DataFundacaoParcial : ICampo<LocalDate?> {

    val data: LocalDate?

    constructor(data: String?) {
        this.data = DataPassada(data).get()
    }

    override fun get(): LocalDate? {
        return data
    }
}