package br.com.unip.cadastro.exception

import org.springframework.http.HttpStatus

class CadastroNaoEncontradoException : CadastroException {

    constructor() : super(ECodigoErro.CADASTRO_NAO_ENCONTRADO, HttpStatus.NOT_FOUND, "Cadastro não encontrado.")
}