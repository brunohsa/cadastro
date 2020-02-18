package br.com.unip.cadastro.repository

import br.com.unip.cadastro.dto.EnderecoCoordenadasDTO
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository

@Repository
class LocalizacaoRepository(val restRepository: IRestRepository) : ILocalizacaoRepository {

    @Value("\${microservice.localizacao.url}")
    private val LOCALIZACAO_URL = ""

    private val ENDERECO_URL = "v1/enderecos"

    override fun buscarEnderecoPorCEP(cep: String): EnderecoCoordenadasDTO {
        return restRepository.get("$LOCALIZACAO_URL$ENDERECO_URL/$cep", EnderecoCoordenadasDTO::class)
    }
}