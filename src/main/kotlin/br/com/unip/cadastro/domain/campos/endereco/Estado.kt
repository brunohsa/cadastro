package br.com.unip.cadastro.domain.campos.endereco

import br.com.unip.cadastro.domain.campos.CampoObrigatorio
import br.com.unip.cadastro.domain.campos.ICampo
import br.com.unip.cadastro.exception.CampoObrigatorioException
import br.com.unip.cadastro.exception.ECodigoErro.CAMPO_ESTADO_OBRIGATORIO

class Estado : ICampo<String> {

    private val valor: String

    constructor(valor: String?) {
        try {
            this.valor = CampoObrigatorio(valor).get()
        } catch (e: CampoObrigatorioException) {
            throw CampoObrigatorioException("CAMPO_ESTADO_OBRIGATORIO", CAMPO_ESTADO_OBRIGATORIO)
        }
    }

    override fun get(): String {
        return valor
    }
}