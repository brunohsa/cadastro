package br.com.unip.cadastro.service

import br.com.unip.cadastro.dto.*

interface IHorariosFuncionamentoService {

    fun buscarPorCadastroUUID(cadastroUUID: String): List<HorarioFuncionamentoDTO>

    fun alterarHorarioFuncionamento(cadastroUUID: String, dto: AlterarHorarioFuncionamentoDTO)

    fun adicionarHorarioDiferenciado(cadastroUUID: String, adicionarHorarioDiferenciado: AdicionarHorarioDiferenciadoDTO)

    fun buscarHorariosDiferenciado(cadastroUUID: String, fitro: FiltroHorarioDiferenciadoDTO): List<HorarioDiferenciadoDTO>

    fun removerHorarioDiferenciado(idHorario: Long)
}