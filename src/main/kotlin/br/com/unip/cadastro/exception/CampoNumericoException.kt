package br.com.unip.cadastro.exception

import org.springframework.http.HttpStatus

class CampoNumericoException : CadastroBaseException {

    constructor() : this(ECodigoErro.CAMPO_DEVE_SER_NUMERICO)

    constructor(codigoErro: ECodigoErro) : super(codigoErro, HttpStatus.BAD_REQUEST)
}