package br.com.unip.cadastro.service

import br.com.unip.cadastro.dto.CadastroDTO
import br.com.unip.cadastro.dto.PessoaFisicaDTO

interface IPessoaFisicaService {

    fun cadastrar(dto: PessoaFisicaDTO): String

    fun buscar(uuid: String?): CadastroDTO
}