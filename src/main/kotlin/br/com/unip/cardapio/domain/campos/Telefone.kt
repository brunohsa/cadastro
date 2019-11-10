package br.com.unip.cardapio.domain.campos

class Telefone : ICampo<String> {

    private val TAMANHO_LIMITE = 11

    val telefone: String

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