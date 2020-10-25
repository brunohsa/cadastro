package br.com.unip.cadastro.dto

import br.com.unip.cadastro.repository.entity.enums.EDiaSemana

class HorarioFuncionamentoDTO(val id: Long,
                              val dia: EDiaSemana,
                              val abertura: String,
                              val fechamento: String,
                              val fechado: Boolean)