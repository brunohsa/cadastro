package br.com.unip.cadastro.repository

import br.com.unip.cadastro.domain.CadastroDomain
import br.com.unip.cadastro.domain.EnderecoDomain
import br.com.unip.cadastro.dto.CadastroDTO
import br.com.unip.cadastro.dto.EnderecoDTO
import br.com.unip.cadastro.repository.entity.Cadastro

interface ICadastroRepository {

    fun cadastrar(domain: CadastroDomain): String

    fun isCadastroValido(uuid: String): Boolean

    fun isCadastroCompleto(uuid: String): Boolean

    fun buscar(uuid: String): CadastroDTO?

    fun buscarPorUUID(uuid: String): Cadastro

    fun adicionarEndereco(domain: EnderecoDomain, uuid: String)

    fun buscarEndereco(uuid: String): EnderecoDTO?

    fun salvar(cadastro: Cadastro)
}