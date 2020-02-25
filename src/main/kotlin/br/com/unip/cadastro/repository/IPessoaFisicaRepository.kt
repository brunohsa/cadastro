package br.com.unip.cadastro.repository

import br.com.unip.cadastro.domain.PessoaFisicaAlteradaDomain
import br.com.unip.cadastro.dto.PessoaFisicaDTO

interface IPessoaFisicaRepository {

    fun alterar(cadastroUUID: String, domain: PessoaFisicaAlteradaDomain)

    fun buscarPorCadastroUUID(cadastroUUID: String): PessoaFisicaDTO
}