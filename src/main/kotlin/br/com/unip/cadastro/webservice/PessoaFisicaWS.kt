package br.com.unip.cadastro.webservice

import br.com.unip.autenticacaolib.util.AutenthicationUtil
import br.com.unip.cadastro.dto.DocumentoDTO
import br.com.unip.cadastro.dto.PessoaFisicaAlteradaDTO
import br.com.unip.cadastro.dto.PessoaFisicaDTO
import br.com.unip.cadastro.repository.entity.enums.ETipoDocumento
import br.com.unip.cadastro.security.filter.Permissoes
import br.com.unip.cadastro.security.filter.Permissoes.ALTERAR_PESSOA_FISICA
import br.com.unip.cadastro.security.filter.Permissoes.BUSCAS_PESSOA_FISICA
import br.com.unip.cadastro.service.IPessoaFisicaService
import br.com.unip.cadastro.webservice.model.request.AlterarDadosPFRequest
import br.com.unip.cadastro.webservice.model.request.PessoaFisicaRequest
import br.com.unip.cadastro.webservice.model.response.PessoaResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/v1/pessoa-fisica"])
class PessoaFisicaWS(val pessoaFisicaService: IPessoaFisicaService) {

    @PostMapping(value = ["/cadastrar"])
    fun cadastrar(@RequestBody request: PessoaFisicaRequest): ResponseEntity<String> {
        val documento = montarDocumentoDTO(request.cpf)
        val dto = PessoaFisicaDTO(request.nome, request.sobrenome, request.telefone, request.dataNascimento, documento)

        val uuid = pessoaFisicaService.cadastrar(dto)
        return ResponseEntity.ok(uuid)
    }

    @GetMapping(value = [""])
    @PreAuthorize("hasAuthority('${BUSCAS_PESSOA_FISICA}')")
    fun buscar(): ResponseEntity<PessoaResponse> {
        val pessoa = pessoaFisicaService.buscarPorCadastroUUID(getCadastroUUID())
        return ResponseEntity.ok(map(pessoa))
    }

    @PutMapping(value = ["/alterar"])
    @PreAuthorize("hasAuthority('${ALTERAR_PESSOA_FISICA}')")
    fun alterar(@RequestBody request: AlterarDadosPFRequest): ResponseEntity<Void> {
        val dto = PessoaFisicaAlteradaDTO(request.sobrenome, request.telefone, request.dataNascimento, request.cpf)
        pessoaFisicaService.alterar(getCadastroUUID(), dto)
        return ResponseEntity.ok().build()
    }

    private fun map(pessoaDTO: PessoaFisicaDTO): PessoaResponse {
        return PessoaResponse(pessoaDTO.nome!!, pessoaDTO.sobrenome, pessoaDTO.telefone, pessoaDTO.dataNascimento)
    }

    private fun montarDocumentoDTO(cpf: String?): DocumentoDTO? {
        return if (!cpf.isNullOrEmpty()) DocumentoDTO(ETipoDocumento.CPF, cpf) else null
    }

    private fun getCadastroUUID(): String {
        val dadosUsuario = AutenthicationUtil.getDadosUsuarioLogado()
        return dadosUsuario.cadastroUUID
    }
}