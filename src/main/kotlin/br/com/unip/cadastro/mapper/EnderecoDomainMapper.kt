package br.com.unip.cadastro.mapper

import br.com.unip.cadastro.domain.EnderecoDomain
import br.com.unip.cadastro.dto.EnderecoCoordenadasDTO
import org.springframework.stereotype.Component

@Component
class EnderecoDomainMapper : IMapper<EnderecoCoordenadasDTO, EnderecoDomain> {

    override fun map(objeto: EnderecoCoordenadasDTO): EnderecoDomain {
        return EnderecoDomain(objeto.cep,
                objeto.bairro,
                objeto.cidade,
                objeto.estado,
                objeto.logradouro,
                objeto.numero,
                objeto.latitude,
                objeto.longitude
        )
    }
}