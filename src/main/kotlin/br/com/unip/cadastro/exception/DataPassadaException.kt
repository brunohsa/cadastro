package br.com.unip.cadastro.exception

import org.springframework.http.HttpStatus

class DataPassadaException : CadastroException {

    constructor() : super(ECodigoErro.DATA_DEVE_SER_RETROATIVA, HttpStatus.BAD_REQUEST)

    constructor(mensagem: String) : this(mensagem, ECodigoErro.DATA_DEVE_SER_RETROATIVA)

    constructor(mensagem: String, codigoErro: ECodigoErro) : super(codigoErro, HttpStatus.BAD_REQUEST, mensagem)
}