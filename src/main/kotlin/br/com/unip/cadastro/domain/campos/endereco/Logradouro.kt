package br.com.unip.cadastro.domain.campos.endereco

import br.com.unip.cadastro.domain.campos.CampoObrigatorio
import br.com.unip.cadastro.domain.campos.ICampo

class Logradouro : ICampo<String> {

    private val nome: String

    constructor(nome: String?) {
        //TODO tratar as exceptions corretamente
        this.nome = CampoObrigatorio(nome).get()
    }

    override fun get(): String {
        return nome
    }
}