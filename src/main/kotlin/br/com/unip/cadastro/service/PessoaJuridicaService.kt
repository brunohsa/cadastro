package br.com.unip.cadastro.service

import br.com.unip.cadastro.domain.CadastroDomain
import br.com.unip.cadastro.dto.CadastroDTO
import br.com.unip.cadastro.dto.PessoaJuridicaDTO
import br.com.unip.cadastro.exception.CadastroNaoEncontradoException
import br.com.unip.cadastro.exception.CampoObrigatorioException
import br.com.unip.cadastro.exception.ECodigoErro
import br.com.unip.cadastro.mapper.PessoaDomainMapper
import br.com.unip.cadastro.repository.ICadastroRepository
import br.com.unip.cadastro.repository.entity.enums.EStatusCadastro.COMPLETO
import org.springframework.stereotype.Service

@Service
class PessoaJuridicaService(val pessoaCompletaMapper: PessoaDomainMapper,
                            val cadastroRepository: ICadastroRepository) : IPessoaJuridicaService {

    override fun cadastrar(dto: PessoaJuridicaDTO): String {
        val pessoaDomain = pessoaCompletaMapper.map(dto)
        return cadastroRepository.cadastrar(CadastroDomain(pessoaDomain, COMPLETO))
    }

    override fun buscar(uuid: String?): CadastroDTO {
        if (uuid.isNullOrEmpty()) {
            throw CampoObrigatorioException(ECodigoErro.UUID_CADASTRO_OBRIGATORIO)
        }
        return cadastroRepository.buscar(uuid) ?: throw CadastroNaoEncontradoException()
    }
}