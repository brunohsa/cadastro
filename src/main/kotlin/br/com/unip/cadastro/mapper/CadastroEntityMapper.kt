package br.com.unip.cadastro.mapper

import br.com.unip.cadastro.domain.CadastroDomain
import br.com.unip.cadastro.repository.entity.Cadastro
import org.springframework.stereotype.Component

@Component
class CadastroEntityMapper : IMapper<CadastroDomain, Cadastro> {

    override fun map(domain: CadastroDomain): Cadastro {
        return Cadastro(domain.status)
    }
}