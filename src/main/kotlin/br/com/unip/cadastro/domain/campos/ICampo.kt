package br.com.unip.cadastro.domain.campos

interface ICampo<T> {

    fun get(): T
}