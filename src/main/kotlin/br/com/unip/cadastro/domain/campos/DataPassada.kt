package br.com.unip.cadastro.domain.campos

import br.com.unip.cadastro.exception.DataPassadaException
import java.time.LocalDate

class DataPassada : ICampo<LocalDate?> {

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
        if (!dataPassada.isBefore(LocalDate.now())) {
            throw DataPassadaException()
        }
        return dataPassada
    }
}