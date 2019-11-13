package br.com.unip.cardapio.repository

import br.com.unip.cardapio.domain.completo.CadastroCompletoDomain
import br.com.unip.cardapio.domain.parcial.CadastroParcialDomain
import br.com.unip.cardapio.dto.CadastroDTO
import br.com.unip.cardapio.dto.PessoaDTO
import br.com.unip.cardapio.repository.entity.Cadastro
import br.com.unip.cardapio.repository.entity.Pessoa
import br.com.unip.cardapio.repository.entity.enums.EStatusCadastro
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.transaction.Transactional


@Repository
class CadastroRepositoryBean(val pessoaRepository: IPessoaRepository,
                             val em: EntityManager) : ICadastroRepository {

    @Transactional
    override fun cadastrar(domain: CadastroParcialDomain): String {
        val pessoa: Pessoa = pessoaRepository.criar(domain.pessoa)
        val cadastro = Cadastro(pessoa, domain.status)

        em.persist(cadastro)
        return cadastro.uuid
    }

    override fun isCadastroValido(uuid: String): Boolean {
        val sql = "SELECT COUNT(c) FROM ${Cadastro::class.qualifiedName} c " +
                "WHERE c.uuid = :uuid"

        val query = em.createQuery(sql)
        query.setParameter("uuid", uuid)

        return query.singleResult as Long > 0
    }

    override fun isCadastroCompleto(uuid: String): Boolean {
        val sql = "SELECT COUNT(c) FROM ${Cadastro::class.qualifiedName} c " +
                "WHERE c.uuid = :uuid AND c.status = :status"

        val query = em.createQuery(sql)
        query.setParameter("uuid", uuid)
        query.setParameter("status", EStatusCadastro.COMPLETO)

        return query.singleResult as Long > 0
    }

    @Transactional
    override fun atualizar(domain: CadastroCompletoDomain, uuid: String) {
        val cadastro: Cadastro = this.buscarPorUUID(uuid)
        cadastro.status = domain.status

        pessoaRepository.atualizar(domain.pessoa, cadastro.getPessoa())
        em.persist(cadastro)
    }

    private fun buscarPorUUID(uuid: String): Cadastro {
        val sql = "SELECT c FROM ${Cadastro::class.qualifiedName} c " +
                "WHERE c.uuid = :uuid "

        val query = em.createQuery(sql, Cadastro::class.java)
        query.setParameter("uuid", uuid)

        return query.singleResult
    }

    override fun buscar(uuid: String): CadastroDTO? {
        val cadastro = buscarPorUUID(uuid) ?: return null

        val pessoa = cadastro.getPessoa()
        val pessoaDTO = PessoaDTO(pessoa.nome, pessoa.tipoDocumento.name, pessoa.numero)
        return CadastroDTO(cadastro.uuid, cadastro.status.name, pessoaDTO)
    }

}