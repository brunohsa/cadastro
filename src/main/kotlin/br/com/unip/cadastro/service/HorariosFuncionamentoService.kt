package br.com.unip.cadastro.service

import br.com.unip.cadastro.domain.FiltroHorarioDiferenciadoDomain
import br.com.unip.cadastro.domain.HorarioDiferenciadoDomain
import br.com.unip.cadastro.domain.HorarioFuncionamentoDomain
import br.com.unip.cadastro.dto.*
import br.com.unip.cadastro.exception.ECodigoErro
import br.com.unip.cadastro.exception.ParametroInvalidoException
import br.com.unip.cadastro.repository.IHorarioDiferenciadoRepository
import br.com.unip.cadastro.repository.IHorarioFuncionamentoRepository
import org.springframework.stereotype.Service

@Service
class HorariosFuncionamentoService(val horarioFuncionamentoRepository: IHorarioFuncionamentoRepository,
                                   val horarioDiferenciadoRepository: IHorarioDiferenciadoRepository) : IHorariosFuncionamentoService {

    override fun buscarPorCadastroUUID(cadastroUUID: String): List<HorarioFuncionamentoDTO> {
        return horarioFuncionamentoRepository.buscarECriarSeNecessario(cadastroUUID)
    }

    override fun alterarHorarioFuncionamento(cadastroUUID: String, dto: AlterarHorarioFuncionamentoDTO) {
        val domain = HorarioFuncionamentoDomain(dto.dia, dto.abertura, dto.fechamento, dto.fechado)
        horarioFuncionamentoRepository.alterar(cadastroUUID, domain)
    }

    override fun adicionarHorarioDiferenciado(cadastroUUID: String, dto: AdicionarHorarioDiferenciadoDTO) {
        val domain = HorarioDiferenciadoDomain(dto.dataEspecial, dto.abertura, dto.fechamento)
        val horarioPersistido =
                horarioDiferenciadoRepository.buscarHorarioDiferenciadoPorDia(cadastroUUID, domain.dataEspecial.get())
        if (horarioPersistido != null) {
            throw ParametroInvalidoException(ECodigoErro.DATA_ESPECIAL_JA_CADASTADA)
        }
        horarioDiferenciadoRepository.adicionar(cadastroUUID, domain)
    }

    override fun buscarHorariosDiferenciado(cadastroUUID: String, fitro: FiltroHorarioDiferenciadoDTO): List<HorarioDiferenciadoDTO> {
        val domain = FiltroHorarioDiferenciadoDomain(fitro.dataCadastro, fitro.dataEspecialInicio, fitro.dataEspecialFim)
        return horarioDiferenciadoRepository.buscar(cadastroUUID, domain)
    }

    override fun removerHorarioDiferenciado(idHorario: Long) {
        horarioDiferenciadoRepository.deletar(idHorario)
    }
}