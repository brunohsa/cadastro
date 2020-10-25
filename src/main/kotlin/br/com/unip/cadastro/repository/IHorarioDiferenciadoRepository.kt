package br.com.unip.cadastro.repository

import br.com.unip.cadastro.domain.HorarioDiferenciadoDomain
import br.com.unip.cadastro.dto.AdicionarHorarioDiferenciadoDTO
import br.com.unip.cadastro.dto.HorarioDiferenciadoDTO
import br.com.unip.cadastro.repository.entity.HorarioDiferenciado

interface IHorarioDiferenciadoRepository {

    fun buscar(cadastroUUID: String) : List<HorarioDiferenciadoDTO>

    fun adicionar(cadastroUUID: String, domain: HorarioDiferenciadoDomain)
}