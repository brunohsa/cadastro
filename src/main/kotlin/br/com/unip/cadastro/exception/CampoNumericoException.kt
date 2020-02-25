package br.com.unip.cadastro.exception

import org.springframework.http.HttpStatus

class CampoNumericoException : CadastroException {

    constructor() : super(ECodigoErro.CAMPO_DEVE_SER_NUMERICO, HttpStatus.BAD_REQUEST)

    constructor(mensagem: String) : this(mensagem, ECodigoErro.CAMPO_DEVE_SER_NUMERICO)

    constructor(mensagem: String, codigoErro: ECodigoErro) : super(codigoErro, HttpStatus.BAD_REQUEST, mensagem)
}