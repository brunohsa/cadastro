package br.com.unip.cadastro.domain.campos.horarios

import br.com.unip.cadastro.domain.campos.CampoOpcional
import br.com.unip.cadastro.domain.campos.ICampo

class Abertura : ICampo<String?> {

    private val campo: String?

    constructor(campo: String?) {
        this.campo = CampoOpcional(campo).get()
    }

    override fun get(): String? {
        return campo
    }
}