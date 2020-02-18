package br.com.unip.cadastro.domain.campos.pessoa.parcial

import br.com.unip.cadastro.domain.campos.pessoa.CampoCPFValido
import br.com.unip.cadastro.domain.campos.ICampo

class CPFParcial : ICampo<String> {

    private val cpf: String

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