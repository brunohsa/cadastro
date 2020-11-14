package br.com.unip.cadastro.repository

import br.com.unip.cadastro.domain.CadastroDomain
import br.com.unip.cadastro.domain.EnderecoDomain
import br.com.unip.cadastro.dto.*
import br.com.unip.cadastro.mapper.CadastroEntityMapper
import br.com.unip.cadastro.mapper.PessoaEntityMapper
import br.com.unip.cadastro.repository.entity.*
import br.com.unip.cadastro.repository.entity.enums.EStatusCadastro
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.NoResultException
import javax.transaction.Transactional


@Repository
class CadastroRepository(val cadastroEntityMapper: CadastroEntityMapper,
                         val pessoaEntityMapper: PessoaEntityMapper,
                         val em: EntityManager) : ICadastroRepository {

    @Transactional
    override fun salvar(cadastro: Cadastro) {
        em.merge(cadastro)
    }

    @Transactional
    override fun cadastrar(domain: CadastroDomain): String {
        val cadastro = cadastroEntityMapper.map(domain)
        val pessoa = pessoaEntityMapper.map(domain.pessoa)
        pessoa.adicionarCadastro(cadastro)

        em.persist(pessoa)
        return cadastro.uuid
    }

    override fun isCadastroValido(uuid: String): Boolean {
        val sql = """SELECT COUNT(c) FROM ${Cadastro::class.qualifiedName} c
                     WHERE c.uuid = :uuid"""

        val query = em.createQuery(sql)
        query.setParameter("uuid", uuid)

        return query.singleResult as Long > 0
    }

    override fun isCadastroCompleto(uuid: String): Boolean {
        val sql = """SELECT COUNT(c) FROM ${Cadastro::class.qualifiedName} c
                     WHERE c.uuid = :uuid AND c.status = :status"""

        val query = em.createQuery(sql)
        query.setParameter("uuid", uuid)
        query.setParameter("status", EStatusCadastro.COMPLETO)

        return query.singleResult as Long > 0
    }

    override fun buscarPorUUID(uuid: String): Cadastro {
        val sql = """SELECT c FROM ${Cadastro::class.qualifiedName} c
                     WHERE c.uuid = :uuid """

        val query = em.createQuery(sql, Cadastro::class.java)
        query.setParameter("uuid", uuid)

        return query.singleResult
    }

    override fun buscarEndereco(uuid: String): EnderecoDTO? {
        val sql = """SELECT e FROM ${Cadastro::class.qualifiedName} c
                     JOIN c.pessoa p
                     JOIN p.endereco e
                     WHERE c.uuid = :uuid """

        val query = em.createQuery(sql, Endereco::class.java)
        query.setParameter("uuid", uuid)

        return try {
            map(query.singleResult)
        } catch (e: NoResultException) {
            null
        }
    }

    @Transactional
    override fun adicionarEndereco(domain: EnderecoDomain, uuid: String) {
        val endereco = Endereco(
                cep = domain.cep.get(),
                bairro = domain.bairro.get(),
                cidade = domain.cidade.get(),
                estado = domain.estado.get(),
                logradouro = domain.logradouro.get(),
                numero = domain.numero.get(),
                latitude = domain.latitude.get(),
                longitude = domain.longitude.get()
        )
        val cadastro = buscarPorUUID(uuid)
        val pessoa = cadastro.getPessoa()
        pessoa.adicionarEndereco(endereco)

        em.persist(cadastro)
    }

    override fun buscar(uuid: String): CadastroDTO? {
        val cadastro = buscarPorUUID(uuid)
        val pessoa = mapPessoa(cadastro.getPessoa())
        return CadastroDTO(cadastro.uuid, cadastro.status.name, pessoa)
    }

    private fun mapPessoa(pessoa: Pessoa): IPessoaDTO {
        if (pessoa is PessoaFisica) {
            return pessoa.toDTO()
        }
        return (pessoa as PessoaJuridica).toDTO()
    }

    private fun PessoaFisica.toDTO() =
            PessoaFisicaDTO(this.nome, this.sobrenome, this.telefone, map(this.getDocumento()))

    private fun PessoaJuridica.toDTO() =
            PessoaJuridicaDTO(this.nome, this.nomeFantasia, this.telefone, this.getDocumento()?.numero, map(this.getEndereco()))

    private fun map(endereco: Endereco?): EnderecoDTO? {
        if (endereco == null) {
            return null
        }
        return EnderecoDTO(endereco.codigoPostal, endereco.bairro, endereco.cidade, endereco.estado,
                endereco.logradouro, endereco.numero)
    }

    private fun map(documento: Documento?): DocumentoDTO? {
        if (documento == null) {
            return null
        }
        return DocumentoDTO(documento.tipoDocumento, documento.numero)
    }
}