package br.com.unip.cadastro.exception

import org.springframework.http.HttpStatus

class CampoObrigatorioException : CadastroException {


    constructor() : super(ECodigoErro.CAMPO_OBRIGATORIO, HttpStatus.BAD_REQUEST)

    constructor(mensagem: String) : this(mensagem, ECodigoErro.CAMPO_OBRIGATORIO)

    constructor(mensagem: String, codigoErro: ECodigoErro) : super(codigoErro, HttpStatus.BAD_REQUEST, mensagem)
}