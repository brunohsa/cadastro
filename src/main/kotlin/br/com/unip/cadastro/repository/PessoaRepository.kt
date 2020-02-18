package br.com.unip.cadastro.repository

import br.com.unip.cadastro.domain.IPessoaDomain
import br.com.unip.cadastro.domain.completo.PessoaFisicaCompletaDomain
import br.com.unip.cadastro.domain.completo.PessoaJuridicaCompletaDomain
import br.com.unip.cadastro.dto.PessoaDTO
import br.com.unip.cadastro.mapper.PessoaEntityMapper
import br.com.unip.cadastro.repository.entity.Pessoa
import br.com.unip.cadastro.repository.entity.PessoaFisica
import br.com.unip.cadastro.repository.entity.PessoaJuridica
import br.com.unip.cadastro.repository.util.PersistenciaUtil
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.transaction.Transactional

@Repository
class PessoaRepository(val em: EntityManager,
                       val pessoaEntityMapper: PessoaEntityMapper) : IPessoaRepository {

    @Transactional
    override fun criar(domain: IPessoaDomain): Pessoa {
        val pessoa = pessoaEntityMapper.map(domain)
        em.persist(pessoa)

        return pessoa
    }

    @Transactional
    override fun atualizar(domain: IPessoaDomain, pessoa: Pessoa): Pessoa {
        if (pessoa is PessoaFisica) {
            this.atualizarPF(pessoa, domain as PessoaFisicaCompletaDomain)
        } else if (pessoa is PessoaJuridica) {
            this.atualizarPJ(pessoa, domain as PessoaJuridicaCompletaDomain)
        }
        return em.merge(pessoa)
    }

    private fun atualizarPF(pf: PessoaFisica, pfDomain: PessoaFisicaCompletaDomain) {
        pf.dataNascimento = pfDomain.dataNascimento.get()
        pf.telefone = pfDomain.telefone.get()
    }

    private fun atualizarPJ(pj: PessoaJuridica, pjDomain: PessoaJuridicaCompletaDomain) {
        pj.telefone = pjDomain.telefone.get()
    }

    override fun buscar(id: Long): Pessoa {
        return PersistenciaUtil.inicializarERemoverProxy(em.find(Pessoa::class.java, id))
    }
}