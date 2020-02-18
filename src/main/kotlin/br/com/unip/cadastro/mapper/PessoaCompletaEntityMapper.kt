package br.com.unip.cadastro.mapper

import br.com.unip.cadastro.domain.completo.IPessoaCompletaDomain
import br.com.unip.cadastro.domain.completo.PessoaFisicaCompletaDomain
import br.com.unip.cadastro.domain.completo.PessoaJuridicaCompletaDomain
import br.com.unip.cadastro.repository.entity.Documento
import br.com.unip.cadastro.repository.entity.Pessoa
import br.com.unip.cadastro.repository.entity.PessoaFisica
import br.com.unip.cadastro.repository.entity.PessoaJuridica
import br.com.unip.cadastro.repository.entity.enums.ETipoDocumento.CNPJ
import br.com.unip.cadastro.repository.entity.enums.ETipoDocumento.CPF
import org.springframework.stereotype.Component

@Component
class PessoaCompletaEntityMapper : IMapper<IPessoaCompletaDomain, Pessoa> {

    override fun map(pessoa: IPessoaCompletaDomain): Pessoa {
        if (pessoa is PessoaFisicaCompletaDomain) {
            return pessoaFisicaParser(pessoa)
        }
        return pessoaJuridicaParser(pessoa as PessoaJuridicaCompletaDomain)
    }

    private fun pessoaFisicaParser(domain: PessoaFisicaCompletaDomain): PessoaFisica {
        return PessoaFisica(nome = domain.nome.get(),
                sobrenome = domain.sobrenome.get(),
                telefone = domain.telefone.get(),
                dataNascimento = domain.dataNascimento.get(),
                documento = Documento(domain.cpf.get(), CPF),
                endereco = null
        )
    }

    private fun pessoaJuridicaParser(domain: PessoaJuridicaCompletaDomain): PessoaJuridica {
        return PessoaJuridica(nome = domain.razaoSocial.get(),
                nomeFantasia = domain.nomeFantasia.get(),
                telefone = domain.telefone.get(),
                documento = Documento(domain.cnpj.get(), CNPJ),
                endereco = null)
    }
}