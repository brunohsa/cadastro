package br.com.unip.cardapio.webservice

import br.com.unip.cardapio.dto.PessoaFisicaDTO
import br.com.unip.cardapio.service.IPessoaService
import br.com.unip.cardapio.webservice.model.request.PessoaFisicaRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/pessoa-fisica"])
class PessoaFisicaWS(val pessoaService: IPessoaService) {

    @RequestMapping(value = ["/cadastrar"], method = [RequestMethod.POST])
    fun cadastrarPessoaFisica(@RequestBody request: PessoaFisicaRequest): ResponseEntity<String> {
        val dto = PessoaFisicaDTO(request.nome,
                request.sobrenome,
                request.telefone,
                request.dataNascimento,
                request.cpf)

        val uuidPessoa = pessoaService.cadastrarPessoaFisica(dto)
        return ResponseEntity.ok(uuidPessoa)
    }
}