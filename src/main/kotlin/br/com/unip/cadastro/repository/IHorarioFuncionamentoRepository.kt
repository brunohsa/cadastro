package br.com.unip.cadastro.repository

import br.com.unip.cadastro.dto.HorarioFuncionamentoDTO

interface IHorarioFuncionamentoRepository {

    fun buscarECriarSeNecessario(cadastroUUID: String): List<HorarioFuncionamentoDTO>
}