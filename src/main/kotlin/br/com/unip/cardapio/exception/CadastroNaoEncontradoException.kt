package br.com.unip.cardapio.exception

import org.springframework.http.HttpStatus

class CadastroNaoEncontradoException : CadastroException {

    constructor() : super(ECodigoErro.CAD018, HttpStatus.NOT_FOUND, "Cadastro não encontrado.")
}