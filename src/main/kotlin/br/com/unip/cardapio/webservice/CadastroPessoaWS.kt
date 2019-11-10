package br.com.unip.cardapio.webservice

import br.com.unip.cardapio.dto.PessoaFisicaDTO
import br.com.unip.cardapio.dto.PessoaJuridicaDTO
import br.com.unip.cardapio.service.ICadastroService
import br.com.unip.cardapio.webservice.model.request.CompletarDadosPFRequest
import br.com.unip.cardapio.webservice.model.request.CompletarDadosPJRequest
import br.com.unip.cardapio.webservice.model.request.PessoaFisicaRequest
import br.com.unip.cardapio.webservice.model.request.PessoaJuridicaRequest
import br.com.unip.cardapio.webservice.model.response.Response
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/v1/cadastros"])
class CadastroPessoaWS(val cadastroService: ICadastroService) {

    @RequestMapping(value = ["/pessoa-fisica"], method = [RequestMethod.POST])
    fun cadastrarPessoaFisica(@RequestBody request: PessoaFisicaRequest): ResponseEntity<String> {
        val dto = PessoaFisicaDTO(request.nome, request.sobrenome, request.telefone, request.dataNascimento, request.cpf)
        val uuid = cadastroService.cadastrar(dto)
        return ResponseEntity.ok(uuid)
    }

    @RequestMapping(value = ["/pessoa-juridica"], method = [RequestMethod.POST])
    fun cadastrarPessoaJuridica(@RequestBody request: PessoaJuridicaRequest): ResponseEntity<Response> {
        val dto = PessoaJuridicaDTO(request.nome, request.telefone, request.dataFundacao, request.cnpj)
        val uuid = cadastroService.cadastrar(dto)
        return ResponseEntity.ok(Response(uuid))
    }

    @RequestMapping(value = ["/pessoa-fisica/{cadastro_uuid}/completar-cadastro"], method = [RequestMethod.POST])
    fun completarPessoaFisica(@PathVariable("cadastro_uuid") cadastroUuid: String?,
                              @RequestBody request: CompletarDadosPFRequest): ResponseEntity<Void> {
        val dto = PessoaFisicaDTO(request.sobrenome, request.telefone, request.dataNascimento, request.cpf)
        cadastroService.completarDados(dto, cadastroUuid)
        return ResponseEntity.ok().build()
    }

    @RequestMapping(value = ["/pessoa-juridica/{cadastro_uuid}/completar-cadastro"], method = [RequestMethod.POST])
    fun completarPessoaJuridica(@PathVariable("cadastro_uuid") cadastroUuid: String?,
                                @RequestBody request: CompletarDadosPJRequest): ResponseEntity<Void> {
        val dto = PessoaJuridicaDTO(request.telefone, request.dataFundacao, request.cnpj)
        cadastroService.completarDados(dto, cadastroUuid)
        return ResponseEntity.ok().build()
    }
}