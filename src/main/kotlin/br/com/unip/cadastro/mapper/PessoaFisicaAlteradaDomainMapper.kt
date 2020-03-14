package br.com.unip.cadastro.mapper

import br.com.unip.cadastro.domain.PessoaFisicaAlteradaDomain
import br.com.unip.cadastro.dto.PessoaFisicaAlteradaDTO
import org.springframework.stereotype.Component

@Component
class PessoaFisicaAlteradaDomainMapper : IMapper<PessoaFisicaAlteradaDTO, PessoaFisicaAlteradaDomain> {

    override fun map(objeto: PessoaFisicaAlteradaDTO): PessoaFisicaAlteradaDomain {
        return PessoaFisicaAlteradaDomain(
                objeto.sobrenome,
                objeto.telefone,
                objeto.cpf
        )
    }
}