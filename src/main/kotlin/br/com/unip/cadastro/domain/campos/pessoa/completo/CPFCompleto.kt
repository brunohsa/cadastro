package br.com.unip.cadastro.domain.campos.pessoa.completo

import br.com.unip.cadastro.domain.campos.pessoa.CampoCPFValido
import br.com.unip.cadastro.domain.campos.ICampo

class CPFCompleto : ICampo<String> {

    private val cpf: String

    constructor(cpf: String?) {
        this.cpf = CampoCPFValido(cpf).get()
    }

    override fun get(): String {
        return cpf
    }
}