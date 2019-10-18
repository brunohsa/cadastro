package br.com.unip.cardapio.repository

import br.com.unip.cardapio.domain.PessoaFisicaDomain
import br.com.unip.cardapio.domain.PessoaJuridicaDomain

interface IPessoaRepository {

    fun cadastrarPessoaFisica(pessoaFisicaDomain: PessoaFisicaDomain) : String

    fun cadastrarPessoaJuridica(pessoaJuridicaDomain: PessoaJuridicaDomain): String
}