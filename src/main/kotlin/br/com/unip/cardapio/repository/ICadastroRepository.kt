package br.com.unip.cardapio.repository

import br.com.unip.cardapio.domain.completo.CadastroCompletoDomain
import br.com.unip.cardapio.domain.parcial.CadastroParcialDomain
import br.com.unip.cardapio.dto.CadastroDTO

interface ICadastroRepository {

    fun cadastrar(domain: CadastroParcialDomain): String

    fun isCadastroValido(uuid: String): Boolean

    fun isCadastroCompleto(uuid: String): Boolean

    fun atualizar(domain: CadastroCompletoDomain, uuid: String)

    fun buscar(uuid: String): CadastroDTO?
}