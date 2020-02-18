package br.com.unip.cadastro.repository

import br.com.unip.cadastro.dto.EnderecoCoordenadasDTO

interface ILocalizacaoRepository {

    fun buscarEnderecoPorCEP(cep : String) : EnderecoCoordenadasDTO
}