package br.com.unip.cadastro.service

import br.com.unip.cadastro.dto.CadastroDTO
import br.com.unip.cadastro.dto.EnderecoDTO
import br.com.unip.cadastro.dto.IPessoaDTO

interface ICadastroService {

    fun buscar(uuid: String): CadastroDTO

    fun completarDados(dto: IPessoaDTO, uuid: String?)

    fun adicionarEndereco(dto: EnderecoDTO, uuid: String)

    fun buscarEndereco(uuid: String): EnderecoDTO?
}