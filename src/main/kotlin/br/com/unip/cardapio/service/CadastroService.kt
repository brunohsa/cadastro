package br.com.unip.cardapio.service

import br.com.unip.cardapio.domain.completo.CadastroCompletoDomain
import br.com.unip.cardapio.domain.parcial.CadastroParcialDomain
import br.com.unip.cardapio.domain.parcial.IPessoaParcialDomain
import br.com.unip.cardapio.dto.IPessoaDTO
import br.com.unip.cardapio.exception.CampoObrigatorioException
import br.com.unip.cardapio.exception.ECodigoErro
import br.com.unip.cardapio.exception.ParametroInvalidoException
import br.com.unip.cardapio.mapper.PessoaCompletaDomainMapper
import br.com.unip.cardapio.mapper.PessoaParcialDomainMapper
import br.com.unip.cardapio.repository.ICadastroRepository
import br.com.unip.cardapio.repository.entity.enums.EStatusCadastro
import org.springframework.stereotype.Service

@Service
class CadastroService(val pessoaParcialMapper: PessoaParcialDomainMapper,
                      val pessoaCompletaMapper: PessoaCompletaDomainMapper,
                      val cadastroRepository: ICadastroRepository) : ICadastroService {

    override fun cadastrar(dto: IPessoaDTO): String {
        val pessoaDomain: IPessoaParcialDomain = pessoaParcialMapper.map(dto)
        val cadastroDomain = CadastroParcialDomain(pessoaDomain, getStatusCadastro(pessoaDomain))
        return cadastroRepository.cadastrar(cadastroDomain)
    }

    private fun getStatusCadastro(pessoa: IPessoaParcialDomain): EStatusCadastro {
        return if (pessoa.dadosObrigatoriosPreenchidos()) EStatusCadastro.PARCIAL else EStatusCadastro.COMPLETO
    }

    override fun completarDados(dto: IPessoaDTO, cadastroUuid: String?) {
        if (cadastroUuid.isNullOrEmpty()) {
            throw CampoObrigatorioException("O identificador do cadastro é obrigatório", ECodigoErro.CAD003)
        }
        if (!cadastroRepository.isCadastroValido(cadastroUuid)) {
            throw ParametroInvalidoException("Cadastro informado não existe", ECodigoErro.CAD004)
        }
        if (cadastroRepository.isCadastroCompleto(cadastroUuid)) {
            throw ParametroInvalidoException("Cadastro já está ativado", ECodigoErro.CAD005)
        }
        val pessoaDomain = pessoaCompletaMapper.map(dto)
        val cadastro = CadastroCompletoDomain(pessoaDomain, EStatusCadastro.COMPLETO)

        cadastroRepository.atualizar(cadastro, cadastroUuid)
    }
}