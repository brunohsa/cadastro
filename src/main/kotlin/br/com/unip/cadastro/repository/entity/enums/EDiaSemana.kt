package br.com.unip.cadastro.repository.entity.enums

import br.com.unip.cadastro.exception.DiaDaSemanaInformadoInvalidoException

enum class EDiaSemana {

    SEGUNDA, TERCA, QUARTA, QUINTA, SEXTA, SABADO, DOMINGO;

    companion object {
        fun getDiaSemana(diaSemana: String): EDiaSemana {
            return values().firstOrNull { ds -> ds.name == diaSemana } ?: throw DiaDaSemanaInformadoInvalidoException()
        }
    }
}