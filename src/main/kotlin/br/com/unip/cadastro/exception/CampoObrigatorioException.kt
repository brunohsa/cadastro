package br.com.unip.cadastro.exception

import org.springframework.http.HttpStatus

class CampoObrigatorioException : CadastroBaseException {

    constructor() : this(ECodigoErro.CAMPO_OBRIGATORIO)

    constructor(codigoErro: ECodigoErro) : super(codigoErro, HttpStatus.BAD_REQUEST)
}