package br.com.unip.cadastro.domain.campos.endereco

import br.com.unip.cadastro.domain.campos.CampoNumerico
import br.com.unip.cadastro.domain.campos.CampoObrigatorio
import br.com.unip.cadastro.domain.campos.ICampo

class CodigoPostal : ICampo<String> {

    private val nome: String

    constructor(nome: String?) {
        //TODO tratar as exceptions corretamente
        this.nome = validar(CampoNumerico(CampoObrigatorio(nome)).get())
    }

    private fun validar(cep: String): String {
        if (cep.length != 8) {
            //TODO throw new CodigoPostalInvalidoException()
            throw RuntimeException()
        }
        return cep
    }

    override fun get(): String {
        return nome
    }
}