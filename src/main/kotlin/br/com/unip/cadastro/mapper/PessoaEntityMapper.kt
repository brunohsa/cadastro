package br.com.unip.cadastro.mapper

import br.com.unip.cadastro.domain.IPessoaDomain
import br.com.unip.cadastro.domain.completo.IPessoaCompletaDomain
import br.com.unip.cadastro.domain.parcial.PessoaFisicaParcialDomain
import br.com.unip.cadastro.repository.entity.Pessoa
import org.springframework.stereotype.Component

@Component
class PessoaEntityMapper : IMapper<IPessoaDomain, Pessoa> {

    private val pessoaCompletaEntityMapper: PessoaCompletaEntityMapper
    private val pessoaFisicaParcialEntityMapper: PessoaFisicaParcialEntityMapper

    constructor(pessoaCompletaEntityMapper: PessoaCompletaEntityMapper,
                pessoaFisicaParcialEntityMapper: PessoaFisicaParcialEntityMapper) {
        this.pessoaCompletaEntityMapper = pessoaCompletaEntityMapper
        this.pessoaFisicaParcialEntityMapper = pessoaFisicaParcialEntityMapper
    }

    override fun map(objeto: IPessoaDomain): Pessoa {
        if (objeto is IPessoaCompletaDomain) {
            return pessoaCompletaEntityMapper.map(objeto)
        }
        return pessoaFisicaParcialEntityMapper.map(objeto as PessoaFisicaParcialDomain)
    }
}