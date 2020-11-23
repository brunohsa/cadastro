package br.com.unip.cadastro.service

import br.com.unip.autenticacaolib.util.AuthenticationUtil
import br.com.unip.cadastro.domain.CadastroDomain
import br.com.unip.cadastro.domain.IPessoaDomain
import br.com.unip.cadastro.dto.PessoaFisicaAlteradaDTO
import br.com.unip.cadastro.dto.PessoaFisicaDTO
import br.com.unip.cadastro.exception.DocumentoJaCadastradoException
import br.com.unip.cadastro.exception.UsuarioNaoPossuiCadastroException
import br.com.unip.cadastro.mapper.PessoaDomainMapper
import br.com.unip.cadastro.mapper.PessoaFisicaAlteradaDomainMapper
import br.com.unip.cadastro.repository.ICadastroRepository
import br.com.unip.cadastro.repository.IPessoaFisicaRepository
import br.com.unip.cadastro.repository.entity.enums.EStatusCadastro.COMPLETO
import org.springframework.stereotype.Service

@Service
class PessoaFisicaService(val pessoaDomainMapper: PessoaDomainMapper,
                          val pessoaAlteradaDomainMapper: PessoaFisicaAlteradaDomainMapper,
                          val cadastroRepository: ICadastroRepository,
                          val pfRepository: IPessoaFisicaRepository) : IPessoaFisicaService {

    override fun cadastrar(dto: PessoaFisicaDTO): String {
        val domain: IPessoaDomain = pessoaDomainMapper.map(dto)
        verificarPFComDocumentoDuplicado(dto.documento?.numero)

        return cadastroRepository.cadastrar(CadastroDomain(domain, COMPLETO))
    }

    override fun alterar(dto: PessoaFisicaAlteradaDTO) {
        val domain = pessoaAlteradaDomainMapper.map(dto)
        verificarPFComDocumentoDuplicado(dto.cpf)

        pfRepository.alterar(getCadastroUUID(), domain)
    }

    private fun verificarPFComDocumentoDuplicado(numero: String?) {
        if (numero != null && pfRepository.pessoaComDocumentoDuplicado(numero)) {
            throw DocumentoJaCadastradoException()
        }
    }

    override fun buscar(cadastroUUID: String): PessoaFisicaDTO {
        return pfRepository.buscarPorCadastroUUID(cadastroUUID)
    }

    private fun getCadastroUUID(): String {
        return AuthenticationUtil.getCadastroUUID() ?: throw UsuarioNaoPossuiCadastroException()
    }
}