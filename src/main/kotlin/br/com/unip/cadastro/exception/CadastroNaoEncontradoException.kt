package br.com.unip.cadastro.exception

import org.springframework.http.HttpStatus

class CadastroNaoEncontradoException : CadastroBaseException {

    constructor() : super(ECodigoErro.CADASTRO_NAO_ENCONTRADO, HttpStatus.BAD_REQUEST)
}