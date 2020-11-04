package br.com.unip.cadastro.repository

import br.com.unip.cadastro.domain.FiltroHorarioDiferenciadoDomain
import br.com.unip.cadastro.domain.HorarioDiferenciadoDomain
import br.com.unip.cadastro.dto.HorarioDiferenciadoDTO

interface IHorarioDiferenciadoRepository {

    fun buscar(cadastroUUID: String, domain: FiltroHorarioDiferenciadoDomain): List<HorarioDiferenciadoDTO>

    fun adicionar(cadastroUUID: String, domain: HorarioDiferenciadoDomain)

    fun deletar(id: Long)
}