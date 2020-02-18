package br.com.unip.cadastro.webservice

import br.com.unip.cadastro.dto.PessoaFisicaDTO
import br.com.unip.cadastro.service.ICadastroService
import br.com.unip.cadastro.service.IPessoaFisicaService
import br.com.unip.cadastro.webservice.model.request.CompletarDadosPFRequest
import br.com.unip.cadastro.webservice.model.request.PessoaFisicaRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/v1/pessoa-fisica"])
class PessoaFisicaWS(val pessoaFisicaService: IPessoaFisicaService,
                     val cadastroService: ICadastroService) {

    @PostMapping(value = ["/cadastrar"])
    fun cadastrar(@RequestBody request: PessoaFisicaRequest): ResponseEntity<String> {
        val dto = PessoaFisicaDTO(request.nome, request.sobrenome, request.telefone, request.dataNascimento, request.cpf)
        val uuid = pessoaFisicaService.cadastrar(dto)
        return ResponseEntity.ok(uuid)
    }

    @PutMapping(value = ["/{cadastro_uuid}/completar-dados"])
    fun completarDados(@PathVariable("cadastro_uuid") cadastroUuid: String,
                       @RequestBody request: CompletarDadosPFRequest): ResponseEntity<Void> {
        val dto = PessoaFisicaDTO(request.sobrenome, request.telefone, request.dataNascimento, request.cpf)
        cadastroService.completarDados(dto, cadastroUuid)
        return ResponseEntity.ok().build()
    }
}