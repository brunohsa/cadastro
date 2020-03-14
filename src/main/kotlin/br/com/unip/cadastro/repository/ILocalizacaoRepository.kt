package br.com.unip.cadastro.repository

import br.com.unip.cadastro.dto.CoordenadasDTO

interface ILocalizacaoRepository {

    fun buscarCoordenadasPorCEP(cep : String) : CoordenadasDTO
}