package br.com.unip.cadastro.domain.campos.pessoa.parcial

import br.com.unip.cadastro.domain.campos.DataPassada
import br.com.unip.cadastro.domain.campos.ICampo
import java.time.LocalDate

class DataNascimentoParcial : ICampo<LocalDate?> {

    private val data: LocalDate?

    constructor(data: String?) {
        this.data = DataPassada(data).get()
    }

    override fun get(): LocalDate? {
        return data
    }
}