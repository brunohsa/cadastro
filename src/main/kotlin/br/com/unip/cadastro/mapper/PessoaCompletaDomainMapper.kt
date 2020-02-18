package br.com.unip.cadastro.mapper

import br.com.unip.cadastro.domain.completo.IPessoaCompletaDomain
import br.com.unip.cadastro.domain.completo.PessoaFisicaCompletaDomain
import br.com.unip.cadastro.domain.completo.PessoaJuridicaCompletaDomain
import br.com.unip.cadastro.dto.IPessoaDTO
import br.com.unip.cadastro.dto.PessoaFisicaDTO
import br.com.unip.cadastro.dto.PessoaJuridicaDTO
import org.springframework.stereotype.Component

@Component
class PessoaCompletaDomainMapper : IMapper<IPessoaDTO, IPessoaCompletaDomain> {

    override fun map(objeto: IPessoaDTO): IPessoaCompletaDomain {
        if (objeto is PessoaFisicaDTO) {
            return PessoaFisicaCompletaDomain(objeto.nome, objeto.sobrenome, objeto.telefone, objeto.dataNascimento, objeto.cpf)
        }
        val pjDTO = objeto as PessoaJuridicaDTO
        return PessoaJuridicaCompletaDomain(pjDTO.razaoSocial, pjDTO.nomeFantasia, pjDTO.telefone, pjDTO.cnpj)
    }
}