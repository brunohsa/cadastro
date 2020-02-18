package br.com.unip.cadastro.mapper

import br.com.unip.cadastro.domain.parcial.PessoaFisicaParcialDomain
import br.com.unip.cadastro.dto.PessoaFisicaDTO
import org.springframework.stereotype.Component

@Component
class PessoaFisicaParcialDomainMapper : IMapper<PessoaFisicaDTO, PessoaFisicaParcialDomain> {

    private val enderecoDomainMapper: EnderecoDomainMapper

    constructor(enderecoDomainMapper: EnderecoDomainMapper) {
        this.enderecoDomainMapper = enderecoDomainMapper
    }

    override fun map(objeto: PessoaFisicaDTO): PessoaFisicaParcialDomain {
        return PessoaFisicaParcialDomain(objeto.nome,
                objeto.sobrenome,
                objeto.telefone,
                objeto.dataNascimento,
                objeto.cpf
        )
    }
}