package br.com.unip.cadastro.repository

import br.com.unip.cadastro.domain.IPessoaDomain
import br.com.unip.cadastro.repository.entity.Pessoa

interface IPessoaRepository {

    fun criar(domain: IPessoaDomain): Pessoa

    fun atualizar(domain: IPessoaDomain, pessoa: Pessoa): Pessoa

    fun buscar(id: Long): Pessoa
}