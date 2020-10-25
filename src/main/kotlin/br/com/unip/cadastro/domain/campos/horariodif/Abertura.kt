package br.com.unip.cadastro.domain.campos.horariodif

import br.com.unip.cadastro.domain.campos.CampoObrigatorio
import br.com.unip.cadastro.domain.campos.ICampo
import br.com.unip.cadastro.exception.CampoObrigatorioException
import br.com.unip.cadastro.exception.ECodigoErro.CAMPO_ABERTURA_OBRIGATORIO
import br.com.unip.cadastro.exception.ECodigoErro.CAMPO_TELEFONE_DEVE_SER_NUMERICO

class Abertura : ICampo<String> {

    private val campo: String

    constructor(campo: String?) {
        try {
            this.campo = CampoObrigatorio(campo).get()
        } catch (e: CampoObrigatorioException) {
            throw CampoObrigatorioException(CAMPO_ABERTURA_OBRIGATORIO)
        }
    }

    override fun get(): String {
        return campo
    }
}