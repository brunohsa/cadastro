package br.com.unip.cadastro.domain.campos.pessoa

import br.com.unip.cadastro.domain.campos.ICampo

class Sobrenome : ICampo<String?> {

    private val sobrenome: String?

    constructor(sobrenome: String?) {
        this.sobrenome = sobrenome
    }

    override fun get(): String? {
        return sobrenome
    }
}