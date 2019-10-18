package br.com.unip.cardapio.domain.campos

class Sobrenome : ICampo<String> {

    val sobrenome: String

    constructor(sobrenome: String) {
        this.sobrenome = sobrenome
    }

    override fun get(): String {
        return sobrenome
    }
}