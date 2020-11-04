package br.com.unip.cadastro.domain.campos.horarios.horariofunc

import br.com.unip.cadastro.domain.campos.CampoObrigatorio
import br.com.unip.cadastro.domain.campos.ICampo
import br.com.unip.cadastro.exception.CampoObrigatorioException
import br.com.unip.cadastro.exception.ECodigoErro
import br.com.unip.cadastro.repository.entity.enums.EDiaSemana

class DiaSemana : ICampo<EDiaSemana> {

    private val campo: EDiaSemana

    constructor(campo: String?) {
        try {
            this.campo = EDiaSemana.getDiaSemana(CampoObrigatorio(campo).get())
        } catch (e: CampoObrigatorioException) {
            throw CampoObrigatorioException(ECodigoErro.CAMPO_DIA_SEMANA_OBRIGATORIO)
        }
    }

    override fun get(): EDiaSemana {
        return campo
    }
}