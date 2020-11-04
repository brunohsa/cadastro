package br.com.unip.cadastro.repository

import br.com.unip.cadastro.domain.HorarioFuncionamentoDomain
import br.com.unip.cadastro.dto.HorarioFuncionamentoDTO

interface IHorarioFuncionamentoRepository {

    fun buscarECriarSeNecessario(cadastroUUID: String): List<HorarioFuncionamentoDTO>

    fun alterar(cadastroUUID: String, domain: HorarioFuncionamentoDomain)
}