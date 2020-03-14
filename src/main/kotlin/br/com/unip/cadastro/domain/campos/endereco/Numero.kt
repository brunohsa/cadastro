package br.com.unip.cadastro.domain.campos.endereco

import br.com.unip.cadastro.domain.campos.CampoNumerico
import br.com.unip.cadastro.domain.campos.CampoObrigatorio
import br.com.unip.cadastro.domain.campos.ICampo
import br.com.unip.cadastro.exception.CampoNumericoException
import br.com.unip.cadastro.exception.CampoObrigatorioException
import br.com.unip.cadastro.exception.ECodigoErro.CAMPO_LOGRADOURO_OBRIGATORIO
import br.com.unip.cadastro.exception.ECodigoErro.CAMPO_NUMERO_DEVE_SER_NUMERICO

class Numero : ICampo<String> {

    private val valor: String

    constructor(valor: String?) {
        try {
            this.valor = CampoNumerico(CampoObrigatorio(valor)).get()
        } catch (e: CampoObrigatorioException) {
            throw CampoObrigatorioException(CAMPO_LOGRADOURO_OBRIGATORIO)
        } catch (e : CampoNumericoException) {
            throw CampoNumericoException(CAMPO_NUMERO_DEVE_SER_NUMERICO)
        }
    }

    override fun get(): String {
        return valor
    }
}