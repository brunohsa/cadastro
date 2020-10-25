package br.com.unip.cadastro.repository

import br.com.unip.cadastro.dto.HorarioFuncionamentoDTO
import br.com.unip.cadastro.repository.entity.HorarioFuncionamento
import br.com.unip.cadastro.repository.entity.enums.EDiaSemana
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.transaction.Transactional


@Repository
class HorarioFuncionamentoRepository(val cadastroRepository: ICadastroRepository,
                                     val em: EntityManager) : IHorarioFuncionamentoRepository {

    @Transactional
    override fun buscarECriarSeNecessario(cadastroUUID: String): List<HorarioFuncionamentoDTO> {
        var horarios = buscarPorCadastroUUID(cadastroUUID)
        if (horarios.isNullOrEmpty()) {
            horarios = criarHorariosDeFuncionamento(cadastroUUID)
        }
        return horarios.map { hf -> hf.toDTO() }
    }

    private fun buscarPorCadastroUUID(cadastroUUID: String): List<HorarioFuncionamento> {
        val sql = """SELECT hf FROM ${HorarioFuncionamento::class.qualifiedName} hf  
                     JOIN hf.cadastro c 
                     WHERE c.uuid = :cadastroUUID"""

        val query = em.createQuery(sql)
        query.setParameter("cadastroUUID", cadastroUUID)

        return query.resultList as List<HorarioFuncionamento>
    }

    private fun criarHorariosDeFuncionamento(cadastroUUID: String): List<HorarioFuncionamento> {
        val cadastro = cadastroRepository.buscarPorUUID(cadastroUUID)
        return EDiaSemana.values().map { ds -> em.merge(HorarioFuncionamento(ds, cadastro)) }
    }

    fun HorarioFuncionamento.toDTO() =
            HorarioFuncionamentoDTO(this.id!!, this.diaSemana, this.abertura, this.fechamento, this.fechado!!)
}