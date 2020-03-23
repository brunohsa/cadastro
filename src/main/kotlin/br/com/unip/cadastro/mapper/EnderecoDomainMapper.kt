package br.com.unip.cadastro.mapper

import br.com.unip.cadastro.domain.EnderecoDomain
import br.com.unip.cadastro.dto.EnderecoDTO
import org.springframework.stereotype.Component

@Component
class EnderecoDomainMapper : IMapper<EnderecoDTO, EnderecoDomain> {

    override fun map(objeto: EnderecoDTO): EnderecoDomain {
        val lat = objeto.coordenadas?.latitude ?: 0.0
        val long = objeto.coordenadas?.longitude ?: 0.0
        return EnderecoDomain(objeto.cep,
                objeto.bairro,
                objeto.cidade,
                objeto.estado,
                objeto.logradouro,
                objeto.numero,
                lat,
                long
        )
    }
}