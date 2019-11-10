package br.com.unip.cardapio.domain.campos.parcial

import br.com.unip.cardapio.domain.campos.CampoCNPJValido
import br.com.unip.cardapio.domain.campos.ICampo

class CNPJParcial : ICampo<String> {

    val cnpj: String

    constructor(cnpj: String?) {
        if (cnpj.isNullOrEmpty()) {
            this.cnpj = ""
        } else {
            this.cnpj = CampoCNPJValido(cnpj).get()
        }
    }

    override fun get(): String {
        return cnpj
    }
}