package br.com.unip.cadastro.domain.campos.pessoa.pj

import br.com.unip.cadastro.domain.campos.CampoOpcional
import br.com.unip.cadastro.domain.campos.ICampo

class NomeFantasia : ICampo<String?> {

    private val valor: String?

    constructor(valor: String?) {
        this.valor = CampoOpcional(valor).get()
    }

    override fun get(): String? {
        return valor
    }
}