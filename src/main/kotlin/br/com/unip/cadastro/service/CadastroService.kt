package br.com.unip.cadastro.service

import br.com.unip.cadastro.dto.CadastroDTO
import br.com.unip.cadastro.dto.EnderecoCoordenadasDTO
import br.com.unip.cadastro.dto.EnderecoDTO
import br.com.unip.cadastro.exception.CadastroNaoEncontradoException
import br.com.unip.cadastro.mapper.EnderecoDomainMapper
import br.com.unip.cadastro.repository.ICadastroRepository
import br.com.unip.cadastro.repository.ILocalizacaoRepository
import org.springframework.stereotype.Service

@Service
class CadastroService(val cadastroRepository: ICadastroRepository,
                      val localizacaoRepository: ILocalizacaoRepository,
                      val enderecoDomainMapper: EnderecoDomainMapper) : ICadastroService {

    override fun buscar(uuid: String): CadastroDTO {
        return cadastroRepository.buscar(uuid) ?: throw CadastroNaoEncontradoException()
    }

    override fun adicionarEndereco(dto: EnderecoDTO, uuid: String) {
        val enderecoDTO = localizacaoRepository.buscarEnderecoPorCEP(dto.cep!!)
        val endereco = EnderecoCoordenadasDTO(dto.cep,
                dto.bairro,
                dto.cidade,
                dto.estado,
                dto.logradouro,
                dto.numero,
                enderecoDTO.latitude,
                enderecoDTO.longitude
        )
        val domain = enderecoDomainMapper.map(endereco)
        cadastroRepository.adicionarEndereco(domain, uuid)
    }

    override fun buscarEndereco(uuid: String): EnderecoDTO? {
        return cadastroRepository.buscarEndereco(uuid)
    }
}