package br.com.unip.cadastro.mapper

import br.com.unip.cadastro.domain.CadastroDomain
import br.com.unip.cadastro.repository.entity.Cadastro
import org.springframework.stereotype.Component

@Component
class CadastroEntityMapper : IMapper<CadastroDomain, Cadastro> {

    private val pessoaEntityMapper: PessoaEntityMapper

    constructor(pessoaEntityMapper: PessoaEntityMapper) {
        this.pessoaEntityMapper = pessoaEntityMapper
    }

    override fun map(domain: CadastroDomain): Cadastro {
        val pessoa = pessoaEntityMapper.map(domain.pessoa)
        return Cadastro(pessoa, domain.status)
    }
}