package br.com.unip.cadastro.domain.campos.pessoa

import br.com.unip.cadastro.domain.campos.CampoNumerico
import br.com.unip.cadastro.domain.campos.CampoObrigatorio
import br.com.unip.cadastro.domain.campos.CampoOpcional
import br.com.unip.cadastro.domain.campos.CampoTamanhoLimite
import br.com.unip.cadastro.domain.campos.ICampo
import br.com.unip.cadastro.exception.CampoExcedeTamanhoLimiteException
import br.com.unip.cadastro.exception.CampoNumericoException
import br.com.unip.cadastro.exception.CampoObrigatorioException
import br.com.unip.cadastro.exception.ECodigoErro.CAMPO_TELEFONE_DEVE_SER_NUMERICO
import br.com.unip.cadastro.exception.ECodigoErro.CAMPO_TELEFONE_EXCEDE_TAMANHO_LIMITE
import br.com.unip.cadastro.exception.ECodigoErro.RAZAO_SOCIAL_OBRIGATORIO

class Telefone : ICampo<String> {

    private val TAMANHO_LIMITE = 11

    private val REGEX_NUMEROS: Regex = "[^0-9]".toRegex()

    private val telefone: String

    constructor(telefone: String?) {
        if (telefone.isNullOrEmpty()) {
            this.telefone = ""
        } else {
            try {
                val telefoneSemMascara = telefone.replace(REGEX_NUMEROS, "")
                this.telefone = CampoTamanhoLimite(CampoNumerico(CampoOpcional(telefoneSemMascara)), TAMANHO_LIMITE).get()
            } catch (e: CampoNumericoException) {
                throw CampoNumericoException(CAMPO_TELEFONE_DEVE_SER_NUMERICO)
            } catch (e: CampoExcedeTamanhoLimiteException) {
                throw CampoExcedeTamanhoLimiteException(CAMPO_TELEFONE_EXCEDE_TAMANHO_LIMITE)
            }
        }
    }

    override fun get(): String {
        return telefone
    }
}