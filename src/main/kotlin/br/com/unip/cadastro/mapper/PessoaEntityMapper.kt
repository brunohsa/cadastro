package br.com.unip.cadastro.mapper

import br.com.unip.cadastro.domain.IPessoaDomain
import br.com.unip.cadastro.domain.PessoaFisicaDomain
import br.com.unip.cadastro.domain.PessoaJuridicaDomain
import br.com.unip.cadastro.repository.entity.Documento
import br.com.unip.cadastro.repository.entity.Pessoa
import br.com.unip.cadastro.repository.entity.PessoaFisica
import br.com.unip.cadastro.repository.entity.PessoaJuridica
import br.com.unip.cadastro.repository.entity.enums.ETipoDocumento.CNPJ
import br.com.unip.cadastro.repository.entity.enums.ETipoDocumento.CPF
import org.springframework.stereotype.Component

@Component
class PessoaEntityMapper : IMapper<IPessoaDomain, Pessoa> {

    override fun map(pessoa: IPessoaDomain): Pessoa {
        if (pessoa is PessoaFisicaDomain) {
            return pessoaFisicaParser(pessoa)
        }
        return pessoaJuridicaParser(pessoa as PessoaJuridicaDomain)
    }

    private fun pessoaFisicaParser(domain: PessoaFisicaDomain): PessoaFisica {
        var documento: Documento? = null

        if (!domain.cpf.get().isNullOrEmpty()) {
            documento = Documento(domain.cpf.get()!!, CPF)
        }
        return PessoaFisica(domain.nome.get(), domain.sobrenome.get(), domain.telefone.get(), documento)
    }

    private fun pessoaJuridicaParser(domain: PessoaJuridicaDomain): PessoaJuridica {
        val documento = Documento(domain.cnpj.get(), CNPJ)
        return PessoaJuridica(domain.razaoSocial.get(), domain.nomeFantasia.get(), domain.telefone.get(), documento)
    }
}