package br.com.unip.cardapio.service

import br.com.unip.cardapio.domain.PessoaFisicaDomain
import br.com.unip.cardapio.domain.PessoaJuridicaDomain
import br.com.unip.cardapio.dto.PessoaFisicaDTO
import br.com.unip.cardapio.dto.PessoaJuridicaDTO
import br.com.unip.cardapio.repository.IPessoaRepository
import org.springframework.stereotype.Service

@Service
class PessoaServiceBean(val pessoaRepository: IPessoaRepository) : IPessoaService {

    override fun cadastrarPessoaFisica(dto: PessoaFisicaDTO): String {
        val domain = PessoaFisicaDomain(dto.nome, dto.sobrenome, dto.telefone, dto.dataNascimento, dto.cpf)
        return pessoaRepository.cadastrarPessoaFisica(domain)
    }

    override fun cadastrarPessoaJuridica(dto: PessoaJuridicaDTO): String {
        val domain = PessoaJuridicaDomain(dto.nome, dto.telefone, dto.dataFundacao, dto.cnpj)
        return pessoaRepository.cadastrarPessoaJuridica(domain)
    }
}