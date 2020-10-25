package br.com.unip.cadastro.service

import br.com.unip.cadastro.dto.CadastroDTO
import br.com.unip.cadastro.dto.PessoaJuridicaDTO

interface IPessoaJuridicaService {

    fun cadastrar(dto: PessoaJuridicaDTO): String

    fun buscarCadastro(uuid: String): CadastroDTO
}