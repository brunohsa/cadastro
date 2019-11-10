package br.com.unip.cardapio.repository

import br.com.unip.cardapio.domain.completo.IPessoaCompletaDomain
import br.com.unip.cardapio.domain.completo.PessoaFisicaCompletaDomain
import br.com.unip.cardapio.domain.completo.PessoaJuridicaCompletaDomain
import br.com.unip.cardapio.domain.parcial.IPessoaParcialDomain
import br.com.unip.cardapio.mapper.PessoaParcialEntityMapper
import br.com.unip.cardapio.repository.entity.Pessoa
import br.com.unip.cardapio.repository.entity.PessoaFisica
import br.com.unip.cardapio.repository.entity.PessoaJuridica
import br.com.unip.cardapio.repository.entity.enums.ETipoDocumento
import br.com.unip.cardapio.repository.util.PersistenciaUtil
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.transaction.Transactional

@Repository
class PessoaRepository(val em: EntityManager,
                       val pessoaParcialEntityMapper: PessoaParcialEntityMapper) : IPessoaRepository {

    @Transactional
    override fun criar(domain: IPessoaParcialDomain): Pessoa {
        val pessoa = pessoaParcialEntityMapper.map(domain)
        em.persist(pessoa)

        return pessoa
    }

    @Transactional
    override fun atualizar(domain: IPessoaCompletaDomain, pessoa: Pessoa): Pessoa {
        if (pessoa is PessoaFisica) {
            this.atualizarPF(pessoa, domain as PessoaFisicaCompletaDomain)
        } else if (pessoa is PessoaJuridica) {
            this.atualizarPJ(pessoa, domain as PessoaJuridicaCompletaDomain)
        }
        return em.merge(pessoa)
    }

    private fun atualizarPF(pf: PessoaFisica, pfDomain: PessoaFisicaCompletaDomain) {
        pf.sobrenome = pfDomain.sobrenome.get()
        pf.dataNascimento = pfDomain.dataNascimento.get()
        pf.telefone = pfDomain.telefone.get()

        if (pf.numero.isNullOrEmpty()) {
            pf.numero = pfDomain.cpf.get()
            pf.tipoDocumento = ETipoDocumento.CPF
        }
    }

    private fun atualizarPJ(pj: PessoaJuridica, pjDomain: PessoaJuridicaCompletaDomain) {
        pj.dataFundacao = pjDomain.dataFundacao.get()
        pj.telefone = pjDomain.telefone.get()

        if (pj.numero.isNullOrEmpty()) {
            pj.numero = pjDomain.cnpj.get()
            pj.tipoDocumento = ETipoDocumento.CNPJ
        }
    }

    override fun buscar(id: Long): Pessoa {
        return PersistenciaUtil.inicializarERemoverProxy(em.find(Pessoa::class.java, id))
    }
}