package br.com.unip.cadastro.repository

import br.com.unip.cadastro.domain.IPessoaDomain
import br.com.unip.cadastro.repository.entity.Pessoa

interface IPessoaRepository {

    fun salvar(domain: IPessoaDomain): Pessoa

    fun buscar(id: Long): Pessoa

    fun buscarPorCadastroUUID(uuid: String): Pessoa
}