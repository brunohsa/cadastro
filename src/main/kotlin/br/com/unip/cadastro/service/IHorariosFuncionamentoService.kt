package br.com.unip.cadastro.service

import br.com.unip.cadastro.dto.AdicionarHorarioDiferenciadoDTO
import br.com.unip.cadastro.dto.HorarioDiferenciadoDTO
import br.com.unip.cadastro.dto.HorarioFuncionamentoDTO

interface IHorariosFuncionamentoService {

    fun buscarPorCadastroUUID(cadastroUUID: String): List<HorarioFuncionamentoDTO>

    fun adicionarHorarioDiferenciado(cadastroUUID: String, adicionarHorarioDiferenciado: AdicionarHorarioDiferenciadoDTO)

    fun buscarHorariosDiferenciado(cadastroUUID: String): List<HorarioDiferenciadoDTO>
}