package br.com.unip.cadastro.exception

import org.springframework.http.HttpStatus

class DiaDaSemanaInformadoInvalidoException : CadastroBaseException {

    constructor() : super(ECodigoErro.DIA_DA_SEMANA_INVALIDO, HttpStatus.BAD_REQUEST)
}