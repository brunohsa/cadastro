package br.com.unip.cadastro.repository

import br.com.unip.cadastro.domain.PessoaFisicaAlteradaDomain
import br.com.unip.cadastro.dto.PessoaFisicaDTO
import br.com.unip.cadastro.exception.CadastroNaoEncontradoException
import br.com.unip.cadastro.repository.entity.Documento
import br.com.unip.cadastro.repository.entity.PessoaFisica
import br.com.unip.cadastro.repository.entity.enums.ETipoDocumento
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.NoResultException
import javax.transaction.Transactional

@Repository
class PessoaFisicaRepository(val em: EntityManager) : IPessoaFisicaRepository {

    @Transactional
    override fun alterar(cadastroUUID: String, domain: PessoaFisicaAlteradaDomain) {
        val pessoa = this.buscarPessoaPorCadastroUUID(cadastroUUID)

        pessoa.sobrenome = domain.sobrenome.get()
        pessoa.telefone = domain.telefone.get()

        if (pessoa.dataNascimento == null) {
            pessoa.dataNascimento = domain.dataNascimento.get()
        }

        val cpf = domain.cpf.get()
        val documento = pessoa.getDocumento()
        if (documento == null && !cpf.isNullOrEmpty()) {
            val doc = Documento(cpf, ETipoDocumento.CPF)
            pessoa.adicionarDocumento(doc)
        }

        em.persist(pessoa)
    }

    private fun buscarPessoaPorCadastroUUID(cadastroUUID: String): PessoaFisica {
        val sql = """
            SELECT p FROM ${PessoaFisica::class.qualifiedName} p
            JOIN p.cadastro c
            WHERE c.uuid = :uuid
        """

        val query = em.createQuery(sql, PessoaFisica::class.java)
        query.setParameter("uuid", cadastroUUID)

        try {
            return query.singleResult
        } catch (e : NoResultException) {
            throw CadastroNaoEncontradoException()
        }
    }

    override fun buscarPorCadastroUUID(cadastroUUID: String): PessoaFisicaDTO {
        val sql = """
            SELECT new ${PessoaFisicaDTO::class.qualifiedName}(p.nome, p.sobrenome, p.telefone) 
            FROM ${PessoaFisica::class.qualifiedName} p
            JOIN p.cadastro c
            WHERE c.uuid = :uuid
        """

        val query = em.createQuery(sql, PessoaFisicaDTO::class.java)
        query.setParameter("uuid", cadastroUUID)

        try {
            return query.singleResult
        } catch (e : NoResultException) {
            throw CadastroNaoEncontradoException()
        }
    }
}