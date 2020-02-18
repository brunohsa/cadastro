package br.com.unip.cadastro.webservice

import br.com.unip.cadastro.dto.CadastroDTO
import br.com.unip.cadastro.dto.DocumentoDTO
import br.com.unip.cadastro.dto.EnderecoDTO
import br.com.unip.cadastro.service.ICadastroService
import br.com.unip.cadastro.webservice.model.request.EnderecoRequest
import br.com.unip.cadastro.webservice.model.response.CadastroResponse
import br.com.unip.cadastro.webservice.model.response.DocumentoResponse
import br.com.unip.cadastro.webservice.model.response.EnderecoResponse
import br.com.unip.cadastro.webservice.model.response.PessoaResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/v1/cadastros"])
class CadastroWS(val cadastroService: ICadastroService) {

    @GetMapping(value = ["/{uuid}"])
    fun buscar(@PathVariable("uuid") uuid: String): ResponseEntity<CadastroResponse> {
        val cadastroDTO = cadastroService.buscar(uuid)
        return ResponseEntity.ok(map(cadastroDTO))
    }

    @GetMapping(value = ["/{uuid}/endereco"])
    fun buscarEndereco(@PathVariable("uuid") uuid: String): ResponseEntity<Any> {
        val endereco = cadastroService.buscarEndereco(uuid)
        return if (endereco == null) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(map(endereco)!!)
        }
    }

    @PutMapping(value = ["/{uuid}/endereco"])
    fun adicionarEndereco(@PathVariable("uuid") uuid: String,
                          @RequestBody endereco: EnderecoRequest) {
        val dto = EnderecoDTO(endereco.cep,
                endereco.bairro,
                endereco.cidade,
                endereco.estado,
                endereco.logradouro,
                endereco.numero
        )
        cadastroService.adicionarEndereco(dto, uuid)
    }

    private fun map(cadastroDTO: CadastroDTO): CadastroResponse {
        val pessoa = cadastroDTO.pessoa
        val documento = map(pessoa.documento)

        val pessoaResponse = PessoaResponse(pessoa.nome, documento)
        return CadastroResponse(cadastroDTO.uuid, cadastroDTO.status, pessoaResponse)
    }

    private fun map(documento: DocumentoDTO): DocumentoResponse {
        return DocumentoResponse(documento.tipo.name, documento.numero)
    }

    private fun map(endereco: EnderecoDTO?): EnderecoResponse? {
        if (endereco == null) {
            return null
        }
        return EnderecoResponse(endereco.cep, endereco.bairro, endereco.cidade, endereco.estado,
                endereco.logradouro, endereco.numero)
    }
}