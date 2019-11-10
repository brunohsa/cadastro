package br.com.unip.cardapio.domain.campos.parcial

import br.com.unip.cardapio.domain.campos.CampoCPFValido
import br.com.unip.cardapio.domain.campos.ICampo

class CPFParcial : ICampo<String> {

    val cpf: String

    constructor(cpf: String?) {
        if (cpf.isNullOrEmpty()) {
            this.cpf = ""
        } else {
            this.cpf = CampoCPFValido(cpf).get()
        }
    }

    override fun get(): String {
        return cpf
    }
}