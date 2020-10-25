package br.com.unip.cadastro.service

import br.com.unip.cadastro.domain.HorarioDiferenciadoDomain
import br.com.unip.cadastro.dto.AdicionarHorarioDiferenciadoDTO
import br.com.unip.cadastro.dto.HorarioDiferenciadoDTO
import br.com.unip.cadastro.dto.HorarioFuncionamentoDTO
import br.com.unip.cadastro.repository.IHorarioDiferenciadoRepository
import br.com.unip.cadastro.repository.IHorarioFuncionamentoRepository
import org.springframework.stereotype.Service

@Service
class HorariosFuncionamentoService(val horarioFuncionamentoRepository: IHorarioFuncionamentoRepository,
                                   val horarioDiferenciadoRepository: IHorarioDiferenciadoRepository) : IHorariosFuncionamentoService {

    override fun buscarPorCadastroUUID(cadastroUUID: String): List<HorarioFuncionamentoDTO> {
        return horarioFuncionamentoRepository.buscarECriarSeNecessario(cadastroUUID)
    }

    override fun adicionarHorarioDiferenciado(cadastroUUID: String, dto: AdicionarHorarioDiferenciadoDTO) {
        val domain = HorarioDiferenciadoDomain(dto.dataEspecial, dto.abertura, dto.fechamento)
        horarioDiferenciadoRepository.adicionar(cadastroUUID, domain)
    }

    override fun buscarHorariosDiferenciado(cadastroUUID: String): List<HorarioDiferenciadoDTO> {
        return horarioDiferenciadoRepository.buscar(cadastroUUID)
    }
}