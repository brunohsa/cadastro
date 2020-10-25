package br.com.unip.cadastro.domain.campos

import br.com.unip.cadastro.exception.ECodigoErro.FORMATO_DATA_INVALIDA
import br.com.unip.cadastro.exception.ParametroInvalidoException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class Data : ICampo<LocalDate?> {

    private val DATE_FORMAT: String = "dd/MM/yyyy"

    private val data: LocalDate?

    constructor(data: String?) {
        try {
            if (data.isNullOrEmpty()) {
                this.data = null
            } else {
                val format: DateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
                this.data = LocalDate.parse(data, format)
            }
        } catch (ex: DateTimeParseException) {
            throw ParametroInvalidoException(FORMATO_DATA_INVALIDA)
        }
    }

    override fun get(): LocalDate? {
        return data
    }
}