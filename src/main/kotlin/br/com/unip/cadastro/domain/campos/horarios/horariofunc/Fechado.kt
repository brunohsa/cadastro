package br.com.unip.cadastro.domain.campos.horarios.horariofunc

import br.com.unip.cadastro.domain.campos.CampoOpcional
import br.com.unip.cadastro.domain.campos.ICampo

class Fechado : ICampo<Boolean?> {

    private val campo: Boolean?

    constructor(campo: Boolean?) {
        this.campo = CampoOpcional(campo).get()
    }

    override fun get(): Boolean? {
        return campo
    }
}