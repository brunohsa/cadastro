package br.com.unip.cadastro.domain

import br.com.unip.cadastro.domain.campos.horarios.Abertura
import br.com.unip.cadastro.domain.campos.horarios.Fechamento
import br.com.unip.cadastro.domain.campos.horarios.horariodif.DataEspecial
import br.com.unip.cadastro.exception.ECodigoErro
import br.com.unip.cadastro.exception.ParametroInvalidoException
import java.time.LocalTime
import java.time.format.DateTimeFormatterBuilder
import java.time.format.DateTimeParseException


class FuncionamentoDomain {

    val abertura: Abertura

    val fechamento: Fechamento

    constructor(abertura: String?, fechamento: String?) {
        this.abertura = Abertura(abertura)
        this.fechamento = Fechamento(fechamento)
        this.validarHorarioFuncionamento()
    }

    private fun validarHorarioFuncionamento() {
        if (abertura.get() == null || fechamento.get() == null) {
            return
        }
        val horarioAbertura = converterHorario(abertura.get())
        val horarioFechamento = converterHorario(fechamento.get())

        if (horarioFechamento.isBefore(horarioAbertura) || horarioAbertura == horarioFechamento) {
            throw ParametroInvalidoException(ECodigoErro.HORARIO_INVALIDO)
        }
    }

    private fun converterHorario(horas: String?): LocalTime {
        val format = DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("H:mm").toFormatter()
        try {
            return LocalTime.parse(horas, format)
        } catch (e: DateTimeParseException) {
            throw ParametroInvalidoException(ECodigoErro.FORMATO_HORARIO_INVALIDO)
        }
    }
}