package br.com.unip.cadastro.domain.campos.endereco

import br.com.unip.cadastro.domain.campos.CampoNumerico
import br.com.unip.cadastro.domain.campos.CampoObrigatorio
import br.com.unip.cadastro.domain.campos.ICampo
import br.com.unip.cadastro.exception.CampoObrigatorioException
import br.com.unip.cadastro.exception.ECodigoErro.*
import br.com.unip.cadastro.exception.ParametroInvalidoException

class CodigoPostal : ICampo<String> {

    private val REGEX_NUMEROS: Regex = "[^0-9]".toRegex()

    private val valor: String

    constructor(valor: String?) {
        try {
            val cepSemMascara = valor?.replace(REGEX_NUMEROS, "")
            this.valor = validar(CampoNumerico(CampoObrigatorio(cepSemMascara)).get())
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