package br.com.unip.cadastro.domain.campos

class CampoOpcional<T> : ICampo<T> {

    private val valor: T

    constructor(valor: T) {
        this.valor = valor
    }

    override fun get(): T {
        return valor
    }
}