package br.com.unip.cardapio.domain.campos.completo

import br.com.unip.cardapio.domain.campos.CampoCPFValido
import br.com.unip.cardapio.domain.campos.ICampo

class CPFCompleto : ICampo<String> {

    val cpf: String

    constructor(cpf: String?) {
        this.cpf = CampoCPFValido(cpf).get()
    }

    override fun get(): String {
        return cpf
    }
}