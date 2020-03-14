package br.com.unip.cadastro.domain.campos.pessoa.pj

import br.com.unip.cadastro.domain.campos.CampoObrigatorio
import br.com.unip.cadastro.domain.campos.ICampo
import br.com.unip.cadastro.exception.CampoObrigatorioException
import br.com.unip.cadastro.exception.ECodigoErro.RAZAO_SOCIAL_OBRIGATORIO

class RazaoSocial : ICampo<String> {

    private val valor: String

    constructor(valor: String?) {
        try {
            this.valor = CampoObrigatorio(valor).get()
        } catch (e: CampoObrigatorioException) {
            throw CampoObrigatorioException(RAZAO_SOCIAL_OBRIGATORIO)
        }
    }

    override fun get(): String {
        return valor
    }
}