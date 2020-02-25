package br.com.unip.cadastro.mapper

import br.com.unip.cadastro.domain.IPessoaDomain
import br.com.unip.cadastro.domain.PessoaFisicaDomain
import br.com.unip.cadastro.domain.PessoaJuridicaDomain
import br.com.unip.cadastro.dto.IPessoaDTO
import br.com.unip.cadastro.dto.PessoaFisicaDTO
import br.com.unip.cadastro.dto.PessoaJuridicaDTO
import org.springframework.stereotype.Component

@Component
class PessoaDomainMapper : IMapper<IPessoaDTO, IPessoaDomain> {

    override fun map(objeto: IPessoaDTO): IPessoaDomain {
        if (objeto is PessoaFisicaDTO) {
            return PessoaFisicaDomain(
                    objeto.nome,
                    objeto.sobrenome,
                    objeto.telefone,
                    objeto.dataNascimento,
                    objeto.documento?.numero
            )
        }
        val pjDTO = objeto as PessoaJuridicaDTO
        return PessoaJuridicaDomain(
                pjDTO.razaoSocial,
                pjDTO.nomeFantasia,
                pjDTO.telefone,
                pjDTO.cnpj
        )
    }
}