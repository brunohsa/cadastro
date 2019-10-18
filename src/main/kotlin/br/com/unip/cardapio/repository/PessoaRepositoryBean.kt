package br.com.unip.cardapio.repository

import br.com.unip.cardapio.domain.PessoaFisicaDomain
import br.com.unip.cardapio.domain.PessoaJuridicaDomain
import br.com.unip.cardapio.repository.entity.PessoaFisica
import br.com.unip.cardapio.repository.entity.PessoaJuridica
import br.com.unip.cardapio.repository.entity.enums.ETipoDocumento
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.transaction.Transactional

@Repository
class PessoaRepositoryBean(val em: EntityManager) : IPessoaRepository {

    @Transactional
    override fun cadastrarPessoaFisica(domain: PessoaFisicaDomain): String {
        val pessoa = PessoaFisica(nome = domain.nome.get(),
                sobrenome = domain.sobrenome.get(),
                telefone = domain.telefone.get(),
                dataNascimento = domain.dataNascimento.get(),
                tipoDocumento = ETipoDocumento.CPF,
                numero = domain.cpf.get())

        em.persist(pessoa)
        return pessoa.uuid
    }

    override fun cadastrarPessoaJuridica(domain: PessoaJuridicaDomain): String {
        val pessoa = PessoaJuridica(nome = domain.nome.get(),
                telefone = domain.telefone.get(),
                dataFundacao = domain.dataFundacao.get(),
                tipoDocumento = ETipoDocumento.CNPJ,
                numero = domain.cnpj.get())

        em.persist(pessoa)
        return pessoa.uuid
    }
}