package br.com.unip.cadastro.exception

import org.springframework.http.HttpStatus
import java.lang.RuntimeException

open class CadastroBaseException : RuntimeException {

    var codigoErro: ECodigoErro

    var httpStatus: HttpStatus

    constructor(codigoErro: ECodigoErro,
                httpStatus: HttpStatus) {
        this.codigoErro = codigoErro
        this.httpStatus = httpStatus
    }
}
