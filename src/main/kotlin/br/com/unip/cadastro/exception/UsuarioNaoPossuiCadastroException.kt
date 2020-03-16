package br.com.unip.cadastro.exception

import org.springframework.http.HttpStatus

class UsuarioNaoPossuiCadastroException : CadastroBaseException {

    constructor() : super(ECodigoErro.USUARIO_NAO_POSSUI_CADASTRO, HttpStatus.NOT_FOUND)
}