package br.com.unip.cadastro.exception

import org.springframework.http.HttpStatus.BAD_REQUEST

class DataFuturaException : CadastroBaseException {

    constructor() : this(ECodigoErro.DATA_DEVE_SER_FUTURA)

    constructor(codigoErro: ECodigoErro) : super(codigoErro, BAD_REQUEST)
}