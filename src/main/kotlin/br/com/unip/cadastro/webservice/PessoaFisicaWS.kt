package br.com.unip.cadastro.webservice

import br.com.unip.cadastro.dto.DocumentoDTO
import br.com.unip.cadastro.dto.PessoaFisicaAlteradaDTO
import br.com.unip.cadastro.dto.PessoaFisicaDTO
import br.com.unip.cadastro.repository.entity.enums.ETipoDocumento
import br.com.unip.cadastro.security.Permissoes.ALTERAR_PESSOA_FISICA
import br.com.unip.cadastro.security.Permissoes.BUSCAR_PESSOA_FISICA
import br.com.unip.cadastro.security.Permissoes.CADASTRAR_PESSOA_FISICA
import br.com.unip.cadastro.service.IPessoaFisicaService
import br.com.unip.cadastro.webservice.model.request.AlterarDadosPFRequest
import br.com.unip.cadastro.webservice.model.request.PessoaFisicaRequest
import br.com.unip.cadastro.webservice.model.response.DocumentoResponse
import br.com.unip.cadastro.webservice.model.response.PessoaResponse
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/v1/pessoa-fisica"])
class PessoaFisicaWS(val pessoaFisicaService: IPessoaFisicaService) {

    @ApiImplicitParams(ApiImplicitParam(name = "token", value = "Token", required = true, paramType = "header"))
    @PostMapping(value = ["/cadastrar"])
    @PreAuthorize("hasAuthority('${CADASTRAR_PESSOA_FISICA}')")
    fun cadastrar(@RequestBody request: PessoaFisicaRequest): ResponseEntity<String> {
        val documento = montarDocumentoDTO(request.cpf)
        val dto = PessoaFisicaDTO(request.nome, request.sobrenome, request.telefone, documento)

        val uuid = pessoaFisicaService.cadastrar(dto)
        return ResponseEntity.ok(uuid)
    }

    @ApiImplicitParams(ApiImplicitParam(name = "token", value = "Token", required = true, paramType = "header"))
    @GetMapping(value = ["/cadastro/{cadastroUUID}/buscar"])
    //@PreAuthorize("hasAuthority('${BUSCAR_PESSOA_FISICA}')")
    fun buscar(@PathVariable("cadastroUUID") cadastroUUID: String): ResponseEntity<PessoaResponse> {
        val pessoa = pessoaFisicaService.buscar(cadastroUUID)
        return ResponseEntity.ok(map(pessoa))
    }

    @ApiImplicitParams(ApiImplicitParam(name = "token", value = "Token", required = true, paramType = "header"))
    @PutMapping(value = ["/alterar"])
    @PreAuthorize("hasAuthority('${ALTERAR_PESSOA_FISICA}')")
    fun alterar(@RequestBody request: AlterarDadosPFRequest): ResponseEntity<Void> {
        val dto = PessoaFisicaAlteradaDTO(request.sobrenome, request.telefone, request.cpf)
        pessoaFisicaService.alterar(dto)
        return ResponseEntity.ok().build()
    }

    private fun map(pessoaDTO: PessoaFisicaDTO): PessoaResponse {
        val docResponse = map(pessoaDTO.documento)
        return PessoaResponse(pessoaDTO.nome!!, pessoaDTO.sobrenome, pessoaDTO.telefone, docResponse)
    }

    private fun map(documento: DocumentoDTO?): DocumentoResponse? {
        if (documento == null) {
            return null
        }
        return DocumentoResponse(documento.tipo.name, documento.numero)
    }

    private fun montarDocumentoDTO(cpf: String?): DocumentoDTO? {
        return if (!cpf.isNullOrEmpty()) DocumentoDTO(ETipoDocumento.CPF, cpf) else null
    }
}