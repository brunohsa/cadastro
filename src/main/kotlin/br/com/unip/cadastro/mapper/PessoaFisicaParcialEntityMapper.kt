package br.com.unip.cadastro.mapper

import br.com.unip.cadastro.domain.parcial.PessoaFisicaParcialDomain
import br.com.unip.cadastro.repository.entity.Documento
import br.com.unip.cadastro.repository.entity.Pessoa
import br.com.unip.cadastro.repository.entity.PessoaFisica
import br.com.unip.cadastro.repository.entity.enums.ETipoDocumento.CPF
import org.springframework.stereotype.Component

@Component
class PessoaFisicaParcialEntityMapper : IMapper<PessoaFisicaParcialDomain, Pessoa> {

    override fun map(pessoa: PessoaFisicaParcialDomain): Pessoa {
        return PessoaFisica(nome = pessoa.nome.get(),
                sobrenome = pessoa.sobrenome.get(),
                telefone = pessoa.telefone.get(),
                dataNascimento = pessoa.dataNascimento.get(),
                documento = Documento(pessoa.cpf.get(), CPF),
                endereco = null
        )
    }
}