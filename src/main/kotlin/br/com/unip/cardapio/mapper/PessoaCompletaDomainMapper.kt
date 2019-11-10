package br.com.unip.cardapio.mapper

import br.com.unip.cardapio.domain.completo.IPessoaCompletaDomain
import br.com.unip.cardapio.domain.completo.PessoaFisicaCompletaDomain
import br.com.unip.cardapio.domain.completo.PessoaJuridicaCompletaDomain
import br.com.unip.cardapio.dto.IPessoaDTO
import br.com.unip.cardapio.dto.PessoaFisicaDTO
import br.com.unip.cardapio.dto.PessoaJuridicaDTO
import org.springframework.stereotype.Component

@Component
class PessoaCompletaDomainMapper : IMapper<IPessoaDTO, IPessoaCompletaDomain> {

    override fun map(objeto: IPessoaDTO): IPessoaCompletaDomain {
        if (objeto is PessoaFisicaDTO) {
            return PessoaFisicaCompletaDomain(objeto.sobrenome, objeto.telefone, objeto.dataNascimento, objeto.cpf)
        }
        val pjDTO = objeto as PessoaJuridicaDTO
        return PessoaJuridicaCompletaDomain(pjDTO.telefone, pjDTO.dataFundacao, pjDTO.cnpj)
    }
}