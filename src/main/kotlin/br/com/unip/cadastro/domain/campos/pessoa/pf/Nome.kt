package br.com.unip.cadastro.domain.campos.pessoa.pf

import br.com.unip.cadastro.domain.campos.CampoObrigatorio
import br.com.unip.cadastro.domain.campos.ICampo
import br.com.unip.cadastro.exception.CampoObrigatorioException
import br.com.unip.cadastro.exception.ECodigoErro.NOME_OBRIGATORIO

class Nome : ICampo<String> {

    private val nome: String

    constructor(nome: String?) {
        try {
            this.nome = CampoObrigatorio(nome).get()
        } catch (e : CampoObrigatorioException) {
            throw CampoObrigatorioException("Nome obrigatorio", NOME_OBRIGATORIO)
        }
    }

    override fun get(): String {
        return nome
    }
}