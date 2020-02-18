package br.com.unip.cadastro.domain.campos.pessoa

import br.com.unip.cadastro.domain.campos.CampoObrigatorio
import br.com.unip.cadastro.domain.campos.ICampo

class Nome : ICampo<String> {

    private val nome: String

    constructor(nome: String?) {
        this.nome = CampoObrigatorio(nome).get()
    }

    override fun get(): String {
        return nome
    }
}