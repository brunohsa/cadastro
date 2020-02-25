package br.com.unip.cadastro.exception

import org.springframework.http.HttpStatus

class ParametroInvalidoException : CadastroException {

    constructor(mensagem: String) : this(mensagem, ECodigoErro.PARAMETRO_INVALIDO)

    constructor(mensagem: String, codigoErro: ECodigoErro) : super(codigoErro, HttpStatus.BAD_REQUEST, mensagem)
}