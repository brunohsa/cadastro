package br.com.unip.cadastro.domain.campos.endereco

import br.com.unip.cadastro.domain.campos.CampoObrigatorio
import br.com.unip.cadastro.domain.campos.ICampo
import br.com.unip.cadastro.exception.CampoObrigatorioException
import br.com.unip.cadastro.exception.ECodigoErro.CAMPO_CEP_OBRIGATORIO
import br.com.unip.cadastro.exception.ECodigoErro.CAMPO_LOGRADOURO_OBRIGATORIO

class Logradouro : ICampo<String> {

    private val valor: String

    constructor(valor: String?) {
        try {
            this.valor = CampoObrigatorio(valor).get()
        } catch (e: CampoObrigatorioException) {
            throw CampoObrigatorioException("CAMPO_LOGRADOURO_OBRIGATORIO", CAMPO_LOGRADOURO_OBRIGATORIO)
        }
    }

    override fun get(): String {
        return valor
    }
}