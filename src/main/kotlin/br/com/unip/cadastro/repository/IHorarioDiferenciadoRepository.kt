package br.com.unip.cadastro.repository

import br.com.unip.cadastro.domain.FiltroHorarioDiferenciadoDomain
import br.com.unip.cadastro.domain.HorarioDiferenciadoDomain
import br.com.unip.cadastro.dto.HorarioDiferenciadoDTO
import br.com.unip.cadastro.repository.entity.HorarioDiferenciado
import java.time.LocalDate

interface IHorarioDiferenciadoRepository {

    fun buscar(cadastroUUID: String, domain: FiltroHorarioDiferenciadoDomain): List<HorarioDiferenciadoDTO>

    fun adicionar(cadastroUUID: String, domain: HorarioDiferenciadoDomain)

    fun buscarHorarioDiferenciadoPorDia(cadastroUUID: String, data: LocalDate): HorarioDiferenciado?

    fun deletar(id: Long)
}