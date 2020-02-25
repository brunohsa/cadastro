package br.com.unip.cadastro.exception

import org.springframework.http.HttpStatus

class CampoExcedeTamanhoLimiteException : CadastroException {

    constructor() : super(ECodigoErro.CAMPO_TAMANHO_LIMITE_EXCEDENTE, HttpStatus.BAD_REQUEST)

    constructor(mensagem: String) : this(mensagem, ECodigoErro.CAMPO_TAMANHO_LIMITE_EXCEDENTE)

    constructor(mensagem: String, codigoErro: ECodigoErro) : super(codigoErro, HttpStatus.BAD_REQUEST, mensagem)
}