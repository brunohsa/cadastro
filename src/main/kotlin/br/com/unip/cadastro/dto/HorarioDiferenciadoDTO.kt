package br.com.unip.cadastro.dto

import java.time.LocalDate
import java.time.LocalDateTime

class HorarioDiferenciadoDTO(var id: Long?,
                             val dataCadastro: LocalDateTime?,
                             val dataEspecial: LocalDate?,
                             val abertura: String?,
                             val fechamento: String?)