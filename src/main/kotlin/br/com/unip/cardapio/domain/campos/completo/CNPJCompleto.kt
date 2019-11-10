package br.com.unip.cardapio.domain.campos.completo

import br.com.unip.cardapio.domain.campos.CampoCNPJValido
import br.com.unip.cardapio.domain.campos.ICampo

class CNPJCompleto : ICampo<String> {

    val cnpj: String

    constructor(cnpj: String?) {
        this.cnpj = CampoCNPJValido(cnpj).get()
    }

    override fun get(): String {
        return cnpj
    }
}