package br.com.unip.cardapio.mapper

import br.com.unip.cardapio.domain.parcial.IPessoaParcialDomain
import br.com.unip.cardapio.domain.parcial.PessoaFisicaParcialDomain
import br.com.unip.cardapio.domain.parcial.PessoaJuridicaParcialDomain
import br.com.unip.cardapio.dto.IPessoaDTO
import br.com.unip.cardapio.dto.PessoaFisicaDTO
import br.com.unip.cardapio.dto.PessoaJuridicaDTO
import org.springframework.stereotype.Component

@Component
class PessoaParcialDomainMapper : IMapper<IPessoaDTO, IPessoaParcialDomain> {

    override fun map(objeto: IPessoaDTO): IPessoaParcialDomain {
        if (objeto is PessoaFisicaDTO) {
            return PessoaFisicaParcialDomain(objeto.nome, objeto.sobrenome, objeto.telefone, objeto.dataNascimento, objeto.cpf)
        }
        val pjDTO = objeto as PessoaJuridicaDTO
        return PessoaJuridicaParcialDomain(pjDTO.nome, pjDTO.telefone, pjDTO.dataFundacao, pjDTO.cnpj)
    }
}