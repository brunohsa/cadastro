package br.com.unip.cadastro.webservice

import br.com.unip.cadastro.dto.PessoaJuridicaDTO
import br.com.unip.cadastro.service.IPessoaJuridicaService
import br.com.unip.cadastro.webservice.model.request.PessoaJuridicaRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/v1/pessoa-juridica"])
class PessoaJuridicaWS(val cadastroPJService: IPessoaJuridicaService) {

    @PostMapping(value = ["/cadastrar"])
    fun cadastrar(@RequestBody request: PessoaJuridicaRequest): ResponseEntity<String> {
        val dto = PessoaJuridicaDTO(request.razaoSocial, request.nomeFantasia, request.telefone, request.cnpj)
        val uuid = cadastroPJService.cadastrar(dto)
        return ResponseEntity.ok(uuid)
    }

    @PutMapping(value = ["/{cadastro_uuid}/alterar"])
    fun alterar(@PathVariable("cadastro_uuid") cadastroUuid: String,
                @RequestBody request: PessoaJuridicaRequest): ResponseEntity<Void> {
        return ResponseEntity.ok().build()
    }
}