package br.com.unip.cadastro.webservice

import br.com.unip.autenticacaolib.util.AuthenticationUtil
import br.com.unip.cadastro.dto.EnderecoDTO
import br.com.unip.cadastro.security.Permissoes.ADICIONAR_ENDERECO
import br.com.unip.cadastro.security.Permissoes.BUSCAR_CADASTRO
import br.com.unip.cadastro.security.Permissoes.BUSCAS_ENDERECO
import br.com.unip.cadastro.service.ICadastroService
import br.com.unip.cadastro.webservice.model.request.EnderecoRequest
import br.com.unip.cadastro.webservice.model.response.CadastroResponse
import br.com.unip.cadastro.webservice.model.response.EnderecoResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
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
    @PreAuthorize("hasAuthority('${BUSCAR_CADASTRO}')")
        fun buscar(@PathVariable("uuid") uuid: String): ResponseEntity<CadastroResponse> {
        val cadastroDTO = cadastroService.buscar(uuid)
        val response = CadastroResponse(cadastroDTO.uuid, cadastroDTO.status)
        return ResponseEntity.ok(response)
    }

    @GetMapping(value = ["/endereco"])
    @PreAuthorize("hasAuthority('${BUSCAS_ENDERECO}')")
    fun buscarEndereco(): ResponseEntity<Any> {
        val endereco = cadastroService.buscarEndereco()
        return if (endereco == null) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(map(endereco)!!)
        }
    }

    @PutMapping(value = ["/endereco/adicionar"])
    @PreAuthorize("hasAuthority('${ADICIONAR_ENDERECO}')")
    fun adicionarEndereco(@RequestBody endereco: EnderecoRequest) {
        val dto = EnderecoDTO(endereco.cep, endereco.bairro, endereco.cidade, endereco.estado,
                endereco.logradouro, endereco.numero)

        cadastroService.adicionarEndereco(dto)
    }

    private fun map(endereco: EnderecoDTO?): EnderecoResponse? {
        if (endereco == null) {
            return null
        }
        return EnderecoResponse(endereco.cep, endereco.bairro, endereco.cidade, endereco.estado, endereco.logradouro,
                endereco.numero)
    }
}