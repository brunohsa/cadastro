package br.com.unip.cadastro.domain.campos

import br.com.unip.cadastro.exception.DataFuturaException
import br.com.unip.cadastro.exception.DataPassadaException
import java.time.LocalDate

class DataFutura : ICampo<LocalDate?> {

    private val data: LocalDate?

    constructor(data: String?) {
        this.data = dataPassada(Data(data).get())
    }

    override fun get(): LocalDate? {
        return data
    }

    private fun dataPassada(dataPassada: LocalDate?): LocalDate? {
        if (dataPassada == null) {
            return null
        }
        val hoje = LocalDate.now()
        if (dataPassada.isBefore(hoje) || dataPassada == hoje) {
            throw DataFuturaException()
        }
        return dataPassada
    }
}