package br.com.unip.cadastro.webservice

import br.com.unip.autenticacaolib.util.AuthenticationUtil
import br.com.unip.cadastro.dto.*
import br.com.unip.cadastro.repository.entity.PessoaFisica
import br.com.unip.cadastro.security.Permissoes.ADICIONAR_ENDERECO
import br.com.unip.cadastro.security.Permissoes.BUSCAR_CADASTRO
import br.com.unip.cadastro.security.Permissoes.BUSCAS_ENDERECO
import br.com.unip.cadastro.service.ICadastroService
import br.com.unip.cadastro.webservice.model.request.EnderecoRequest
import br.com.unip.cadastro.webservice.model.response.*
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
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

    @ApiImplicitParams(ApiImplicitParam(name = "token", value = "Token", required = true, paramType = "header"))
    @GetMapping
    @PreAuthorize("hasAuthority('${BUSCAR_CADASTRO}')")
    fun buscar(): ResponseEntity<CadastroResponse> {
        val cadastroUUID = AuthenticationUtil.getCadastroUUID()!!
        val cadastroDTO = cadastroService.buscar(cadastroUUID)
        return ResponseEntity.ok(cadastroDTO.toResponse())
    }

    @ApiImplicitParams(ApiImplicitParam(name = "token", value = "Token", required = true, paramType = "header"))
    @GetMapping("/{cadastro_uuid}")
    @PreAuthorize("hasAuthority('${BUSCAR_CADASTRO}')")
    fun buscarPorCadastroUUID(@PathVariable(value = "cadastro_uuid") uuid: String): ResponseEntity<CadastroResponse> {
        val cadastroDTO = cadastroService.buscar(uuid)
        return ResponseEntity.ok(cadastroDTO.toResponse())
    }

    @ApiImplicitParams(ApiImplicitParam(name = "token", value = "Token", required = true, paramType = "header"))
    @GetMapping(value = ["/endereco"])
    @PreAuthorize("hasAuthority('${BUSCAS_ENDERECO}')")
    fun buscarEndereco(): ResponseEntity<Any> {
        val endereco = cadastroService.buscarEndereco()
        return ResponseEntity.ok(map(endereco)!!)
    }

    @ApiImplicitParams(ApiImplicitParam(name = "token", value = "Token", required = true, paramType = "header"))
    @PutMapping(value = ["/endereco/adicionar"])
    @PreAuthorize("hasAuthority('${ADICIONAR_ENDERECO}')")
    fun adicionarEndereco(@RequestBody endereco: EnderecoRequest) {
        val dto = EnderecoDTO(endereco.cep, endereco.bairro, endereco.cidade, endereco.estado, endereco.logradouro, endereco.numero)
        cadastroService.adicionarEndereco(dto)
    }

    private fun mapPessoa(pessoa: IPessoaDTO): IPessoaResponse {
        if (pessoa is PessoaFisicaDTO) {
            return pessoa.toResponse()
        }
        return (pessoa as PessoaJuridicaDTO).toResponse()
    }

    private fun CadastroDTO.toResponse() =
            CadastroResponse(this.uuid, this.status, mapPessoa(this.pessoa))

    private fun PessoaFisicaDTO.toResponse() =
            PessoaFisicaResponse(this.nome, this.sobrenome, this.telefone, this.documento?.numero)

    private fun PessoaJuridicaDTO.toResponse() =
            PessoaJuridicaResponse(this.razaoSocial, this.nomeFantasia, this.telefone, this.cnpj, map(this.endereco))

    private fun map(endereco: EnderecoDTO?): EnderecoResponse? {
        if (endereco == null) {
            return null
        }
        return EnderecoResponse(endereco.cep,
                endereco.bairro,
                endereco.cidade,
                endereco.estado,
                endereco.logradouro,
                endereco.numero)
    }
}