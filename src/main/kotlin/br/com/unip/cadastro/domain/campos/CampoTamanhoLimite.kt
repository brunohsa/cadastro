package br.com.unip.cadastro.domain.campos

import br.com.unip.cadastro.exception.CampoExcedeTamanhoLimiteException

class CampoTamanhoLimite : ICampo<String> {

    private val campo: ICampo<String>

    constructor(campo: ICampo<String>, maxSize: Int) {
        if (campo.get().length > maxSize) {
            throw CampoExcedeTamanhoLimiteException()
        }
        this.campo = campo
    }

    override fun get(): String {
        return campo.get()
    }
}