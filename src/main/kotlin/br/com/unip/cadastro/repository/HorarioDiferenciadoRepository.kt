package br.com.unip.cadastro.repository

import br.com.unip.cadastro.domain.HorarioDiferenciadoDomain
import br.com.unip.cadastro.dto.HorarioDiferenciadoDTO
import br.com.unip.cadastro.repository.entity.HorarioDiferenciado
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.transaction.Transactional


@Repository
class HorarioDiferenciadoRepository(val cadastroRepository: ICadastroRepository,
                                    val em: EntityManager) : IHorarioDiferenciadoRepository {

    override fun buscar(cadastroUUID: String): List<HorarioDiferenciadoDTO> {
        val sql = """SELECT new ${HorarioDiferenciadoDTO::class.qualifiedName}(hd.id, hd.dataCadastro, hd.dataEspecial, hd.abertura, hd.fechamento)  
                     FROM ${HorarioDiferenciado::class.qualifiedName} hd 
                     JOIN hd.cadastro c
                     WHERE c.uuid = :cadastroUUID
                     ORDER BY hd.dataEspecial desc """

        val query = em.createQuery(sql)
        query.setParameter("cadastroUUID", cadastroUUID)

        return query.resultList as List<HorarioDiferenciadoDTO>
    }

    @Transactional
    override fun adicionar(cadastroUUID: String, domain: HorarioDiferenciadoDomain) {
        val cadastro = cadastroRepository.buscarPorUUID(cadastroUUID)
        val entity = HorarioDiferenciado(dataEspecial = domain.dataEspecial.get(),
                abertura = domain.abertura.get(),
                fechamento = domain.fechamento.get(),
                cadastro = cadastro)

        em.merge(entity)
    }
}