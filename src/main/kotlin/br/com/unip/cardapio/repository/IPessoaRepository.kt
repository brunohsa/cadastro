package br.com.unip.cardapio.repository

import br.com.unip.cardapio.domain.completo.IPessoaCompletaDomain
import br.com.unip.cardapio.domain.parcial.IPessoaParcialDomain
import br.com.unip.cardapio.repository.entity.Pessoa

interface IPessoaRepository {

    fun criar(domain: IPessoaParcialDomain): Pessoa

    fun atualizar(domain: IPessoaCompletaDomain, pessoa: Pessoa): Pessoa

    fun buscar(id: Long): Pessoa
}