package br.com.unip.cardapio.domain.campos

class CNPJ : ICampo<String> {

    val cnpj: String

    constructor(cnpj: String) {
        this.cnpj = cnpjValido(CampoNumerico(CampoObrigatorio(cnpj)).get())
    }

    override fun get(): String {
        return cnpj
    }

    private fun cnpjValido(original: String): String {
        val sb = StringBuilder(original)

        while (sb.length < 14) {
            sb.insert(0, '0')
        }

        val value = sb.toString()

        var adds = 0
        var cnpjCalc = value.substring(0, 12)
        val chrCnpj = value.toCharArray()

        var i: Int = 0
        while (i < 4) {
            if (chrCnpj[i].toInt() - 48 in 0..9) {
                adds += (chrCnpj[i].toInt() - 48) * (6 - (i + 1))
            }
            ++i
        }

        i = 0
        while (i < 8) {
            if (chrCnpj[i + 4].toInt() - 48 in 0..9) {
                adds += (chrCnpj[i + 4].toInt() - 48) * (10 - (i + 1))
            }
            ++i
        }

        var dig = 11 - adds % 11
        cnpjCalc += if (dig != 10 && dig != 11) Integer.toString(dig) else "0"
        adds = 0

        i = 0
        while (i < 5) {
            if (chrCnpj[i].toInt() - 48 in 0..9) {
                adds += (chrCnpj[i].toInt() - 48) * (7 - (i + 1))
            }
            ++i
        }

        i = 0
        while (i < 8) {
            if (chrCnpj[i + 5].toInt() - 48 in 0..9) {
                adds += (chrCnpj[i + 5].toInt() - 48) * (10 - (i + 1))
            }
            ++i
        }

        dig = 11 - adds % 11
        cnpjCalc += if (dig != 10 && dig != 11) Integer.toString(dig) else "0"

        if (value != cnpjCalc) {
            //lancar excecao
        }

        return original
    }
}