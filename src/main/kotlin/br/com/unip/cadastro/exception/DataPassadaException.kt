package br.com.unip.cadastro.exception

import br.com.unip.cadastro.exception.ECodigoErro.DATA_DEVE_SER_RETROATIVA
import org.springframework.http.HttpStatus.BAD_REQUEST

class DataPassadaException : CadastroBaseException {

    constructor() : this(DATA_DEVE_SER_RETROATIVA)

    constructor(codigoErro: ECodigoErro) : super(codigoErro, BAD_REQUEST)
}