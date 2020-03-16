package br.com.unip.cadastro.service

import br.com.unip.autenticacaolib.util.AuthenticationUtil
import br.com.unip.cadastro.dto.CadastroDTO
import br.com.unip.cadastro.dto.EnderecoDTO
import br.com.unip.cadastro.exception.CadastroNaoEncontradoException
import br.com.unip.cadastro.exception.UsuarioNaoPossuiCadastroException
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

    override fun adicionarEndereco(dto: EnderecoDTO) {
        val coordenadas = localizacaoRepository.buscarCoordenadasPorCEP(dto.cep!!)
        val endereco = EnderecoDTO(dto.cep, dto.bairro, dto.cidade, dto.estado, dto.logradouro, dto.numero, coordenadas)
        val domain = enderecoDomainMapper.map(endereco)
        cadastroRepository.adicionarEndereco(domain, getCadastroUUID())
    }

    override fun buscarEndereco(): EnderecoDTO? {
        return cadastroRepository.buscarEndereco(getCadastroUUID())
    }

    private fun getCadastroUUID(): String {
        return AuthenticationUtil.getCadastroUUID() ?: throw UsuarioNaoPossuiCadastroException()
    }
}