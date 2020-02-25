package br.com.unip.cadastro.repository

import br.com.unip.cadastro.domain.IPessoaDomain
import br.com.unip.cadastro.domain.PessoaFisicaDomain
import br.com.unip.cadastro.domain.PessoaJuridicaDomain
import br.com.unip.cadastro.mapper.PessoaEntityMapper
import br.com.unip.cadastro.repository.entity.Pessoa
import br.com.unip.cadastro.repository.entity.PessoaFisica
import br.com.unip.cadastro.repository.entity.PessoaJuridica
import br.com.unip.cadastro.repository.util.PersistenciaUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.transaction.Transactional

@Repository
class PessoaRepository : IPessoaRepository {

    @Autowired
    private lateinit var em: EntityManager

    @Autowired
    private lateinit var pessoaEntityMapper: PessoaEntityMapper

    @Transactional
    override fun salvar(domain: IPessoaDomain): Pessoa {
        val pessoa = pessoaEntityMapper.map(domain)
        em.persist(pessoa)

        return pessoa
    }

    override fun buscar(id: Long): Pessoa {
        return PersistenciaUtil.inicializarERemoverProxy(em.find(Pessoa::class.java, id))
    }

    override fun buscarPorCadastroUUID(uuid: String): Pessoa {
        val sql = """
            SELECT p FROM ${Pessoa::class.qualifiedName} p
            JOIN p.cadastro c
            WHERE c.uuid = :uuid
        """

        val query = em.createQuery(sql, Pessoa::class.java)
        query.setParameter("uuid", uuid)

        return query.singleResult
    }
}