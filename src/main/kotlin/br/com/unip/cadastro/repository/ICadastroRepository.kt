package br.com.unip.cadastro.repository

import br.com.unip.cadastro.domain.CadastroDomain
import br.com.unip.cadastro.domain.EnderecoDomain
import br.com.unip.cadastro.dto.CadastroDTO
import br.com.unip.cadastro.dto.EnderecoDTO

interface ICadastroRepository {

    fun cadastrar(domain: CadastroDomain): String

    fun isCadastroValido(uuid: String): Boolean

    fun isCadastroCompleto(uuid: String): Boolean

    fun atualizar(domain: CadastroDomain, uuid: String)

    fun buscar(uuid: String): CadastroDTO?

    fun adicionarEndereco(domain: EnderecoDomain, uuid: String)

    fun buscarEndereco(uuid: String): EnderecoDTO?
}