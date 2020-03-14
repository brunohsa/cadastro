package br.com.unip.cadastro.repository

import br.com.unip.cadastro.dto.CoordenadasDTO
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository

@Repository
class LocalizacaoRepository(val restRepository: IRestRepository) : ILocalizacaoRepository {

    @Value("\${microservice.localizacao.url}")
    private val LOCALIZACAO_URL = ""

    private val ENDERECO_URL = "v1/enderecos"

    override fun buscarCoordenadasPorCEP(cep: String): CoordenadasDTO {
        return restRepository.get("$LOCALIZACAO_URL$ENDERECO_URL/$cep/coordenadas", CoordenadasDTO::class)
    }
}