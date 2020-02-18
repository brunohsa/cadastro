package br.com.unip.cadastro.service

import br.com.unip.cadastro.domain.CadastroDomain
import br.com.unip.cadastro.dto.CadastroDTO
import br.com.unip.cadastro.dto.EnderecoCoordenadasDTO
import br.com.unip.cadastro.dto.EnderecoDTO
import br.com.unip.cadastro.dto.IPessoaDTO
import br.com.unip.cadastro.exception.CadastroNaoEncontradoException
import br.com.unip.cadastro.exception.CampoObrigatorioException
import br.com.unip.cadastro.exception.ECodigoErro.CAD003
import br.com.unip.cadastro.exception.ECodigoErro.CAD004
import br.com.unip.cadastro.exception.ECodigoErro.CAD005
import br.com.unip.cadastro.exception.ParametroInvalidoException
import br.com.unip.cadastro.mapper.EnderecoDomainMapper
import br.com.unip.cadastro.mapper.PessoaCompletaDomainMapper
import br.com.unip.cadastro.repository.ICadastroRepository
import br.com.unip.cadastro.repository.ILocalizacaoRepository
import br.com.unip.cadastro.repository.entity.enums.EStatusCadastro.COMPLETO
import org.springframework.stereotype.Service

@Service
class CadastroService(val cadastroRepository: ICadastroRepository,
                      val localizacaoRepository: ILocalizacaoRepository,
                      val pessoaCompletaMapper: PessoaCompletaDomainMapper,
                      val enderecoDomainMapper: EnderecoDomainMapper) : ICadastroService {

    override fun buscar(uuid: String): CadastroDTO {
        if (uuid.isNullOrEmpty()) {
            throw CampoObrigatorioException("O identificador do cadastro é obrigatório", CAD003)
        }
        return cadastroRepository.buscar(uuid) ?: throw CadastroNaoEncontradoException()
    }

    override fun completarDados(dto: IPessoaDTO, uuid: String?) {
        if (uuid.isNullOrEmpty()) {
            throw CampoObrigatorioException("O identificador do cadastro é obrigatório", CAD003)
        }
        if (!cadastroRepository.isCadastroValido(uuid)) {
            throw ParametroInvalidoException("Cadastro informado não existe", CAD004)
        }
        if (cadastroRepository.isCadastroCompleto(uuid)) {
            throw ParametroInvalidoException("Cadastro já está ativado", CAD005)
        }
        val domain = pessoaCompletaMapper.map(dto)
        val cadastro = CadastroDomain(domain, COMPLETO)

        cadastroRepository.atualizar(cadastro, uuid)
    }

    override fun adicionarEndereco(dto: EnderecoDTO, uuid: String) {
        if (!cadastroRepository.isCadastroValido(uuid)) {
            throw ParametroInvalidoException("Cadastro informado não existe", CAD004)
        }
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
        if (uuid.isNullOrEmpty()) {
            throw CampoObrigatorioException("O identificador do cadastro é obrigatório", CAD003)
        }
        return cadastroRepository.buscarEndereco(uuid)
    }
}