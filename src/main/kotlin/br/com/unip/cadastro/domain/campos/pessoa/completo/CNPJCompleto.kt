package br.com.unip.cadastro.domain.campos.pessoa.completo

import br.com.unip.cadastro.domain.campos.pessoa.CampoCNPJValido
import br.com.unip.cadastro.domain.campos.ICampo

class CNPJCompleto : ICampo<String> {

    private val cnpj: String

    constructor(cnpj: String?) {
        this.cnpj = CampoCNPJValido(cnpj).get()
    }

    override fun get(): String {
        return cnpj
    }
}