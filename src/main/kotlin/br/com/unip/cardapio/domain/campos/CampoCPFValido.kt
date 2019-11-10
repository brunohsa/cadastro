package br.com.unip.cardapio.domain.campos

import br.com.unip.cardapio.exception.CampoNumericoException
import br.com.unip.cardapio.exception.CampoObrigatorioException
import br.com.unip.cardapio.exception.ECodigoErro
import br.com.unip.cardapio.exception.ParametroInvalidoException

class CampoCPFValido : ICampo<String> {

    val cpf: String

    constructor(cpf: String?) {
        try {
            this.cpf = cpfValido(CampoNumerico(CampoObrigatorio(cpf)).get())
        } catch (e: CampoNumericoException) {
            throw CampoNumericoException("CPF inválido", ECodigoErro.CAD0010)
        } catch (e: CampoObrigatorioException) {
            throw CampoObrigatorioException("CPF é obrigatório", ECodigoErro.CAD011)
        }
    }

    override fun get(): String {
        return cpf
    }

    private fun cpfValido(cpf: String): String {
        var d1 = 0
        var d2 = 0

        for (nCount in 1 until cpf.length - 1) {
            val digitCPF = Integer.parseInt(cpf.substring(nCount - 1, nCount))
            d1 += (11 - nCount) * digitCPF
            d2 += (12 - nCount) * digitCPF
        }

        var remaining = d1 % 11
        val digit1: Int
        if (remaining < 2) {
            digit1 = 0
        } else {
            digit1 = 11 - remaining
        }

        d2 += 2 * digit1
        remaining = d2 % 11
        val digit2: Int
        if (remaining < 2) {
            digit2 = 0
        } else {
            digit2 = 11 - remaining
        }

        val nDigVerific = cpf.substring(cpf.length - 2, cpf.length)
        val nDigResult = digit1.toString() + digit2.toString()

        if (nDigVerific != nDigResult) {
            throw ParametroInvalidoException("CPF inválido", ECodigoErro.CAD0010)
        }

        return cpf
    }
}