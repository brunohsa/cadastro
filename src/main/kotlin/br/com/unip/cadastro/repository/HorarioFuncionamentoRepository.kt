package br.com.unip.cadastro.repository

import br.com.unip.cadastro.domain.HorarioFuncionamentoDomain
import br.com.unip.cadastro.dto.HorarioFuncionamentoDTO
import br.com.unip.cadastro.exception.CadastroNaoEncontradoException
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

    @Transactional
    override fun alterar(cadastroUUID: String, domain: HorarioFuncionamentoDomain) {
        val horario = this.buscarPorCadastroUUIDEDiaDaSemana(cadastroUUID, domain.diaSemana.get())
                ?: throw CadastroNaoEncontradoException()
        horario.abertura = domain.funcionamento.abertura.get()
        horario.fechamento = domain.funcionamento.fechamento.get()
        if (domain.fechado.get() != null) {
            horario.fechado = domain.fechado.get()
        }
        em.merge(horario)
    }

    private fun buscarPorCadastroUUID(cadastroUUID: String): List<HorarioFuncionamento> {
        val sql = """SELECT hf FROM ${HorarioFuncionamento::class.qualifiedName} hf  
                     JOIN hf.cadastro c 
                     WHERE c.uuid = :cadastroUUID"""

        val query = em.createQuery(sql)
        query.setParameter("cadastroUUID", cadastroUUID)

        return query.resultList as List<HorarioFuncionamento>
    }

    private fun buscarPorCadastroUUIDEDiaDaSemana(cadastroUUID: String, diaSemana: EDiaSemana): HorarioFuncionamento? {
        val sql = """SELECT hf FROM ${HorarioFuncionamento::class.qualifiedName} hf  
                     JOIN hf.cadastro c 
                     WHERE c.uuid = :cadastroUUID
                     AND hf.diaSemana = :diaSemana"""

        val query = em.createQuery(sql)
        query.setParameter("cadastroUUID", cadastroUUID)
        query.setParameter("diaSemana", diaSemana)

        return query.singleResult as HorarioFuncionamento
    }

    private fun criarHorariosDeFuncionamento(cadastroUUID: String): List<HorarioFuncionamento> {
        val cadastro = cadastroRepository.buscarPorUUID(cadastroUUID)
        return EDiaSemana.values().map { ds -> em.merge(HorarioFuncionamento(ds, cadastro)) }
    }

    fun HorarioFuncionamento.toDTO() =
            HorarioFuncionamentoDTO(this.id!!, this.diaSemana, this.abertura, this.fechamento, this.fechado!!)
}