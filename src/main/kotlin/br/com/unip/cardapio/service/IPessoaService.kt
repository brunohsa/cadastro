package br.com.unip.cardapio.service

import br.com.unip.cardapio.dto.PessoaFisicaDTO
import br.com.unip.cardapio.dto.PessoaJuridicaDTO

interface IPessoaService {

    fun cadastrarPessoaFisica(pessoaFisicaDTO: PessoaFisicaDTO): String

    fun cadastrarPessoaJuridica(pessoaJuridicaDTO: PessoaJuridicaDTO): String
}