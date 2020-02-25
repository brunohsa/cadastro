package br.com.unip.cadastro.domain.campos.pessoa.pf

import br.com.unip.cadastro.domain.campos.CampoOpcional
import br.com.unip.cadastro.domain.campos.ICampo

class Sobrenome : ICampo<String?> {

    private val sobrenome: String?

    constructor(sobrenome: String?) {
        this.sobrenome = CampoOpcional(sobrenome).get()
    }

    override fun get(): String? {
        return sobrenome
    }
}