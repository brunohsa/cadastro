package br.com.unip.cadastro.domain.campos

import br.com.unip.cadastro.exception.CampoNumericoException

class CampoNumerico : ICampo<String> {

    private val REGEX_NUMEROS: Regex = "-?\\d+(\\.\\d+)?".toRegex()
    private val valor: String

    constructor(valor: ICampo<String>) {
        if (!valor.get().matches(REGEX_NUMEROS)) {
            throw CampoNumericoException()
        }
        this.valor = valor.get()
    }

    override fun get(): String {
        return valor
    }
}