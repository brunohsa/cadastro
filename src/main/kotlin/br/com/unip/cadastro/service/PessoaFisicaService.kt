package br.com.unip.cadastro.service

import br.com.unip.cadastro.domain.CadastroDomain
import br.com.unip.cadastro.domain.IPessoaDomain
import br.com.unip.cadastro.dto.CadastroDTO
import br.com.unip.cadastro.dto.PessoaFisicaDTO
import br.com.unip.cadastro.exception.CadastroNaoEncontradoException
import br.com.unip.cadastro.exception.CampoObrigatorioException
import br.com.unip.cadastro.exception.ECodigoErro
import br.com.unip.cadastro.mapper.IMapper
import br.com.unip.cadastro.mapper.PessoaCompletaDomainMapper
import br.com.unip.cadastro.mapper.PessoaFisicaParcialDomainMapper
import br.com.unip.cadastro.repository.ICadastroRepository
import br.com.unip.cadastro.repository.entity.enums.EStatusCadastro
import br.com.unip.cadastro.repository.entity.enums.EStatusCadastro.COMPLETO
import br.com.unip.cadastro.repository.entity.enums.EStatusCadastro.PARCIAL
import org.springframework.stereotype.Service

@Service
class PessoaFisicaService(val pessoaParcialMapper: PessoaFisicaParcialDomainMapper,
                          val pessoaCompletaMapper: PessoaCompletaDomainMapper,
                          val cadastroRepository: ICadastroRepository) : IPessoaFisicaService {

    override fun cadastrar(dto: PessoaFisicaDTO): String {
        if (cadastroEhCompleto(dto)) {
            return this.cadastrar(dto, pessoaCompletaMapper as IMapper<PessoaFisicaDTO, IPessoaDomain>, COMPLETO)
        }
        return this.cadastrar(dto, pessoaParcialMapper as IMapper<PessoaFisicaDTO, IPessoaDomain>, PARCIAL)
    }

    private fun cadastrar(dto: PessoaFisicaDTO, mapper: IMapper<PessoaFisicaDTO, IPessoaDomain>, status: EStatusCadastro): String {
        val domain: IPessoaDomain = mapper.map(dto)
        return cadastroRepository.cadastrar(CadastroDomain(domain, status))
    }

    private fun cadastroEhCompleto(dto: PessoaFisicaDTO): Boolean {
        return !dto.cpf.isNullOrEmpty() && !dto.nome.isNullOrEmpty()
    }

    override fun buscar(uuid: String?): CadastroDTO {
        if (uuid.isNullOrEmpty()) {
            throw CampoObrigatorioException("O identificador do cadastro é obrigatório", ECodigoErro.CAD003)
        }
        return cadastroRepository.buscar(uuid) ?: throw CadastroNaoEncontradoException()
    }
}