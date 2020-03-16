package br.com.unip.cadastro.exception

import br.com.unip.cadastro.exception.ECodigoErro.DOCUMENTO_JA_CADASTRADO
import org.springframework.http.HttpStatus.BAD_REQUEST

class DocumentoJaCadastradoException : CadastroBaseException {

    constructor() : this(DOCUMENTO_JA_CADASTRADO)

    constructor(codigoErro: ECodigoErro) : super(codigoErro, BAD_REQUEST)
}