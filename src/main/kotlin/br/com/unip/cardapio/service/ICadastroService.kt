package br.com.unip.cardapio.service

import br.com.unip.cardapio.dto.IPessoaDTO

interface ICadastroService {

    fun cadastrar(dto: IPessoaDTO): String

    fun completarDados(dto: IPessoaDTO, cadastroUuid: String?)
}