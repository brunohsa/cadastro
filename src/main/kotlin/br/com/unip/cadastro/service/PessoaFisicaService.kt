package br.com.unip.cadastro.service

import br.com.unip.cadastro.domain.CadastroDomain
import br.com.unip.cadastro.domain.IPessoaDomain
import br.com.unip.cadastro.dto.PessoaFisicaAlteradaDTO
import br.com.unip.cadastro.dto.PessoaFisicaDTO
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
                          val pessoaFisicaRepository: IPessoaFisicaRepository) : IPessoaFisicaService {

    override fun cadastrar(dto: PessoaFisicaDTO): String {
        val domain: IPessoaDomain = pessoaDomainMapper.map(dto)
        return cadastroRepository.cadastrar(CadastroDomain(domain, COMPLETO))
    }

    override fun alterar(uuid: String, dto: PessoaFisicaAlteradaDTO) {
        val domain = pessoaAlteradaDomainMapper.map(dto)
        pessoaFisicaRepository.alterar(uuid, domain)
    }

    override fun buscarPorCadastroUUID(cadastroUUID: String): PessoaFisicaDTO {
        return pessoaFisicaRepository.buscarPorCadastroUUID(cadastroUUID)
    }
}