package br.com.unip.cadastro.repository

import br.com.unip.cadastro.domain.FiltroHorarioDiferenciadoDomain
import br.com.unip.cadastro.domain.HorarioDiferenciadoDomain
import br.com.unip.cadastro.dto.HorarioDiferenciadoDTO
import br.com.unip.cadastro.exception.HorarioDiferenciadoNaoEncontradoException
import br.com.unip.cadastro.repository.entity.HorarioDiferenciado
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.transaction.Transactional


@Repository
class HorarioDiferenciadoRepository(val cadastroRepository: ICadastroRepository,
                                    val em: EntityManager) : IHorarioDiferenciadoRepository {
    override fun buscar(cadastroUUID: String, domain: FiltroHorarioDiferenciadoDomain): List<HorarioDiferenciadoDTO> {
        val parametros = HashMap<String, Any>()
        var sql = """SELECT new ${HorarioDiferenciadoDTO::class.qualifiedName}(hd.id, hd.dataCadastro, hd.dataEspecial, hd.abertura, hd.fechamento)  
                     FROM ${HorarioDiferenciado::class.qualifiedName} hd 
                     JOIN hd.cadastro c
                     WHERE c.uuid = :cadastroUUID """

        if (domain.dataCadastro.get() != null) {
            sql += " AND hd.dataCadastro =:dataCadastro "
            parametros["dataCadastro"] = domain.dataCadastro.get()!!
        }
        if (domain.dataEspecialInicio.get() != null && domain.dataEspecialFim.get() != null) {
            sql += " AND hd.dataEspecial BETWEEN :dataEspecialInicio AND :dataEspecialFim "
            parametros["dataEspecialInicio"] = domain.dataEspecialInicio.get()!!
            parametros["dataEspecialFim"] = domain.dataEspecialFim.get()!!
        } else if (domain.dataEspecialInicio.get() != null) {
            sql += " AND hd.dataEspecial >= :dataEspecialInicio "
            parametros["dataEspecialInicio"] = domain.dataEspecialInicio.get()!!
        } else if (domain.dataEspecialFim.get() != null) {
            sql += " AND hd.dataEspecial <= :dataEspecialFim "
            parametros["dataEspecialFim"] = domain.dataEspecialFim.get()!!
        }
        sql += " ORDER BY hd.dataEspecial desc "

        parametros["cadastroUUID"] = cadastroUUID

        val query = em.createQuery(sql)
        parametros.forEach { (campo, valor) -> query.setParameter(campo, valor) }

        return query.resultList as List<HorarioDiferenciadoDTO>
    }

    @Transactional
    override fun adicionar(cadastroUUID: String, domain: HorarioDiferenciadoDomain) {
        val cadastro = cadastroRepository.buscarPorUUID(cadastroUUID)
        val entity = HorarioDiferenciado(
                domain.dataEspecial.get(),
                domain.funcionamento.abertura.get(),
                domain.funcionamento.fechamento.get(),
                cadastro
        )
        em.merge(entity)
    }

    @Transactional
    override fun deletar(id: Long) {
        val horario = em.find(HorarioDiferenciado::class.java, id) ?: throw  HorarioDiferenciadoNaoEncontradoException()
        em.remove(horario)
    }
}
