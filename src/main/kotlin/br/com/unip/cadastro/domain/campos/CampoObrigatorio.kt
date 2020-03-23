package br.com.unip.cadastro.domain.campos

import br.com.unip.cadastro.exception.CampoObrigatorioException
import org.springframework.util.StringUtils

class CampoObrigatorio<T> : ICampo<T> {

    private val valor: T

    constructor(valor: T?) {
        if (StringUtils.isEmpty(valor)) {
            throw CampoObrigatorioException()
        }
        this.valor = valor!!
    }

    override fun get(): T {
        return valor
    }
}