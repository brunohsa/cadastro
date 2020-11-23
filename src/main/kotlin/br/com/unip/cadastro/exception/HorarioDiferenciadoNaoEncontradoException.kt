package br.com.unip.cadastro.exception

import org.springframework.http.HttpStatus

class HorarioDiferenciadoNaoEncontradoException : CadastroBaseException {

    constructor() : super(ECodigoErro.HORARIO_DIFERENCIADO_NAO_ENCONTRADO, HttpStatus.BAD_REQUEST)
}