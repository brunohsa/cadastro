package br.com.unip.cadastro.exception

import br.com.unip.cadastro.exception.ECodigoErro.PARAMETRO_INVALIDO
import org.springframework.http.HttpStatus.BAD_REQUEST

class ParametroInvalidoException : CadastroBaseException {

    constructor() : this(PARAMETRO_INVALIDO)

    constructor(codigoErro: ECodigoErro) : super(codigoErro, BAD_REQUEST)
}