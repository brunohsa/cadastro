package br.com.unip.cadastro.repository

import br.com.unip.cadastro.domain.PessoaFisicaAlteradaDomain
import br.com.unip.cadastro.dto.DocumentoDTO
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
        } catch (e: NoResultException) {
            throw CadastroNaoEncontradoException()
        }
    }

    @Transactional
    override fun alterar(cadastroUUID: String, domain: PessoaFisicaAlteradaDomain) {
        val pessoa = this.buscarPessoaPorCadastroUUID(cadastroUUID)

        pessoa.sobrenome = domain.sobrenome.get()
        pessoa.telefone = domain.telefone.get()

        val cpf = domain.cpf.get()
        val documento = pessoa.getDocumento()
        if (documento == null && !cpf.isNullOrEmpty()) {
            val doc = Documento(cpf, ETipoDocumento.CPF)
            pessoa.adicionarDocumento(doc)
        }

        em.persist(pessoa)
    }

    override fun buscarPorCadastroUUID(cadastroUUID: String): PessoaFisicaDTO {
        val pessoa = buscarPessoaPorCadastroUUID(cadastroUUID)
        val documento = pessoa.getDocumento()

        var documentoDTO: DocumentoDTO? = null
        if (documento != null) {
            documentoDTO = DocumentoDTO(documento.tipoDocumento, documento.numero)
        }

        return PessoaFisicaDTO(pessoa.nome, pessoa.sobrenome, pessoa.telefone, documentoDTO)
    }

    override fun pessoaComDocumentoDuplicado(numero: String): Boolean {
        val sql = """
            SELECT COUNT(p) 
            FROM ${PessoaFisica::class.qualifiedName} p
            JOIN p.documento d
            WHERE d.tipoDocumento = :tipoDocumento
            AND d.numero = :numero
        """

        val query = em.createQuery(sql)
        query.setParameter("tipoDocumento", ETipoDocumento.CPF)
        query.setParameter("numero", numero)

        return query.singleResult as Long > 0
    }
}