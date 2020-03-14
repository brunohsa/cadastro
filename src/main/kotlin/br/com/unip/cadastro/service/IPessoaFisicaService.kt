package br.com.unip.cadastro.service

import br.com.unip.cadastro.dto.PessoaFisicaAlteradaDTO
import br.com.unip.cadastro.dto.PessoaFisicaDTO

interface IPessoaFisicaService {

    fun cadastrar(dto: PessoaFisicaDTO): String

    fun alterar(dto: PessoaFisicaAlteradaDTO)

    fun buscar(): PessoaFisicaDTO
}