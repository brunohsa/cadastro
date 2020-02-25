package br.com.unip.cadastro.domain.campos.endereco

import br.com.unip.cadastro.domain.campos.CampoObrigatorio
import br.com.unip.cadastro.domain.campos.ICampo
import br.com.unip.cadastro.exception.CampoNumericoException
import br.com.unip.cadastro.exception.CampoObrigatorioException
import br.com.unip.cadastro.exception.ECodigoErro.CAMPO_BAIRRO_OBRIGATORIO
import br.com.unip.cadastro.exception.ECodigoErro.CAMPO_TELEFONE_DEVE_SER_NUMERICO

class Bairro : ICampo<String> {

    private val valor: String

    constructor(valor: String?) {
        try {
            this.valor = CampoObrigatorio(valor).get()
        } catch (e: CampoObrigatorioException) {
            throw CampoObrigatorioException("CAMPO_BAIRRO_OBRIGATORIO", CAMPO_BAIRRO_OBRIGATORIO)
        }
    }

    override fun get(): String {
        return valor
    }
}