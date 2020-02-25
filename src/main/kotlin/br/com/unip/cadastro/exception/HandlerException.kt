package br.com.unip.cadastro.exception

import br.com.unip.cadastro.webservice.model.response.Erro
import br.com.unip.cadastro.webservice.model.response.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class HandlerException {

    @ExceptionHandler(CadastroException::class)
    fun handlerCadastroException(e: CadastroException): ResponseEntity<Response> {
        val erro = Erro(e.codigoErro, e.message ?: "")
        return ResponseEntity.status(e.httpStatus.value()).body(Response(erro))
    }

    @ExceptionHandler(AccessDeniedException::class, AuthenticationCredentialsNotFoundException::class)
    fun handlerAcessoNegado(e: Exception): ResponseEntity<Response> {
        val erro = Erro(ECodigoErro.ACESSO_NEGADO, "Acesso negado.")
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Response(erro))
    }

    @ExceptionHandler(Throwable::class)
    fun handlerErroInesperado(e: Throwable): ResponseEntity<Response> {
        val erro = Erro(ECodigoErro.ERRO_INESPERADO, "Erro inesperado")
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Response(erro))
    }
}