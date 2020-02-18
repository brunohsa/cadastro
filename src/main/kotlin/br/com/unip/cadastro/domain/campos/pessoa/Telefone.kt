package br.com.unip.cadastro.domain.campos.pessoa

import br.com.unip.cadastro.domain.campos.CampoNumerico
import br.com.unip.cadastro.domain.campos.CampoOpcional
import br.com.unip.cadastro.domain.campos.CampoTamanhoLimite
import br.com.unip.cadastro.domain.campos.ICampo

class Telefone : ICampo<String> {

    private val TAMANHO_LIMITE = 11

    private val telefone: String

    constructor(telefone: String?) {
        if (telefone.isNullOrEmpty()) {
            this.telefone = ""
        } else {
            this.telefone = CampoTamanhoLimite(CampoNumerico(CampoOpcional(telefone)), TAMANHO_LIMITE).get()
        }
    }

    override fun get(): String {
        return telefone
    }
}