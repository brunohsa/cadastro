package br.com.unip.cadastro.domain.campos.endereco

import br.com.unip.cadastro.domain.campos.CampoNumerico
import br.com.unip.cadastro.domain.campos.CampoObrigatorio
import br.com.unip.cadastro.domain.campos.ICampo
import br.com.unip.cadastro.exception.CampoObrigatorioException
import br.com.unip.cadastro.exception.ECodigoErro.CAMPO_CEP_DEVE_SER_NUMERICO
import br.com.unip.cadastro.exception.ECodigoErro.CAMPO_CEP_INVALIDO
import br.com.unip.cadastro.exception.ECodigoErro.CAMPO_CEP_OBRIGATORIO
import br.com.unip.cadastro.exception.ECodigoErro.CAMPO_LOGRADOURO_OBRIGATORIO
import br.com.unip.cadastro.exception.ParametroInvalidoException

class CodigoPostal : ICampo<String> {

    private val valor: String

    constructor(valor: String?) {
        try {
            this.valor = validar(CampoNumerico(CampoObrigatorio(valor)).get())
        } catch (e: CampoObrigatorioException) {
            throw CampoObrigatorioException(CAMPO_CEP_OBRIGATORIO)
        } catch (e: CampoObrigatorioException) {
            throw CampoObrigatorioException(CAMPO_CEP_DEVE_SER_NUMERICO)
        }
    }

    private fun validar(cep: String): String {
        if (cep.length != 8) {
            throw ParametroInvalidoException(CAMPO_CEP_INVALIDO)
        }
        return cep
    }

    override fun get(): String {
        return valor
    }
}