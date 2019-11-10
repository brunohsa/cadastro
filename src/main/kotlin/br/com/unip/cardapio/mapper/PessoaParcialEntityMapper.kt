package br.com.unip.cardapio.mapper

import br.com.unip.cardapio.domain.parcial.IPessoaParcialDomain
import br.com.unip.cardapio.domain.parcial.PessoaFisicaParcialDomain
import br.com.unip.cardapio.domain.parcial.PessoaJuridicaParcialDomain
import br.com.unip.cardapio.repository.entity.Pessoa
import br.com.unip.cardapio.repository.entity.PessoaFisica
import br.com.unip.cardapio.repository.entity.PessoaJuridica
import br.com.unip.cardapio.repository.entity.enums.ETipoDocumento
import org.springframework.stereotype.Component

@Component
class PessoaParcialEntityMapper : IMapper<IPessoaParcialDomain, Pessoa> {

    override fun map(objeto: IPessoaParcialDomain): Pessoa {
        if (objeto is PessoaFisicaParcialDomain) {
            return pessoaFisicaParser(objeto)
        }
        return pessoaJuridicaParser(objeto as PessoaJuridicaParcialDomain)
    }

    private fun pessoaFisicaParser(domain: PessoaFisicaParcialDomain): PessoaFisica {
        return PessoaFisica(nome = domain.nome.get(),
                sobrenome = domain.sobrenome.get(),
                telefone = domain.telefone.get(),
                dataNascimento = domain.dataNascimento.get(),
                tipoDocumento = ETipoDocumento.CPF,
                numero = domain.cpf.get())
    }

    private fun pessoaJuridicaParser(domain: PessoaJuridicaParcialDomain): PessoaJuridica {
        return PessoaJuridica(nome = domain.nome.get(),
                telefone = domain.telefone.get(),
                dataFundacao = domain.dataFundacao.get(),
                tipoDocumento = ETipoDocumento.CNPJ,
                numero = domain.cnpj.get())
    }
}