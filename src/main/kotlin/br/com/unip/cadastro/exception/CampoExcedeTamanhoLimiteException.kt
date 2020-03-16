package br.com.unip.cadastro.exception

import br.com.unip.cadastro.exception.ECodigoErro.CAMPO_TAMANHO_LIMITE_EXCEDENTE
import org.springframework.http.HttpStatus.BAD_REQUEST

class CampoExcedeTamanhoLimiteException : CadastroBaseException {

    constructor() : this(CAMPO_TAMANHO_LIMITE_EXCEDENTE)

    constructor(codigoErro: ECodigoErro) : super(codigoErro, BAD_REQUEST)
}