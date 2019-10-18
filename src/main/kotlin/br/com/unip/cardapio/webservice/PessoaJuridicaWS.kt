package br.com.unip.cardapio.webservice

import br.com.unip.cardapio.dto.PessoaJuridicaDTO
import br.com.unip.cardapio.service.IPessoaService
import br.com.unip.cardapio.webservice.model.request.PessoaJuridicaRequest
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/pessoa-juridica"])
class PessoaJuridicaWS(val pessoaService: IPessoaService) {

    @RequestMapping(value = ["/cadastrar"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun cadastrarPessoaFisica(@RequestBody request: PessoaJuridicaRequest): ResponseEntity<String> {
        val dto = PessoaJuridicaDTO(request.nome,
                request.telefone,
                request.dataFundacao,
                request.cnpj)

        val uuidPessoa = pessoaService.cadastrarPessoaJuridica(dto)
        return ResponseEntity.ok(uuidPessoa)
    }
}