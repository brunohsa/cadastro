package br.com.unip.cadastro.webservice

import br.com.unip.autenticacaolib.util.AuthenticationUtil
import br.com.unip.cadastro.dto.*
import br.com.unip.cadastro.security.Permissoes.ALTERAR_PESSOA_JURIDICA
import br.com.unip.cadastro.security.Permissoes.CADASTRAR_PESSOA_JURIDICA
import br.com.unip.cadastro.service.IHorariosFuncionamentoService
import br.com.unip.cadastro.service.IPessoaJuridicaService
import br.com.unip.cadastro.webservice.model.request.HorarioDiferenciadoRequest
import br.com.unip.cadastro.webservice.model.request.PessoaJuridicaRequest
import br.com.unip.cadastro.webservice.model.response.HorarioDiferenciadoResponse
import br.com.unip.cadastro.webservice.model.response.HorarioFuncionamentoRequest
import br.com.unip.cadastro.webservice.model.response.HorarioFuncionamentoResponse
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiParam
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping(value = ["/v1/pessoa-juridica"])
class PessoaJuridicaWS(val cadastroPJService: IPessoaJuridicaService,
                       val horarioFuncionamentoService: IHorariosFuncionamentoService) {

    @ApiImplicitParams(ApiImplicitParam(name = "token", value = "Token", required = true, paramType = "header"))
    @PostMapping(value = ["/cadastrar"])
    @PreAuthorize("hasAuthority('${CADASTRAR_PESSOA_JURIDICA}')")
    fun cadastrar(@RequestBody request: PessoaJuridicaRequest): ResponseEntity<String> {
        val dto = PessoaJuridicaDTO(request.razaoSocial, request.nomeFantasia, request.telefone, request.cnpj)
        val uuid = cadastroPJService.cadastrar(dto)
        return ResponseEntity.ok(uuid)
    }

    @ApiImplicitParams(ApiImplicitParam(name = "token", value = "Token", required = true, paramType = "header"))
    @GetMapping(value = ["/horarios/funcionamento"])
    @PreAuthorize("hasAuthority('${ALTERAR_PESSOA_JURIDICA}')")
    fun buscarHorarioDeFuncionamento(): ResponseEntity<List<HorarioFuncionamentoResponse>> {
        val horarios = horarioFuncionamentoService.buscarPorCadastroUUID(getCadastroUUID())
        return ResponseEntity.ok(horarios.toResponse())
    }

    @ApiImplicitParams(ApiImplicitParam(name = "token", value = "Token", required = true, paramType = "header"))
    @PutMapping(value = ["/horarios/funcionamento"])
    @PreAuthorize("hasAuthority('${ALTERAR_PESSOA_JURIDICA}')")
    fun alterarHorarioFuncionamento(@RequestBody request: HorarioFuncionamentoRequest): ResponseEntity<Void> {
        horarioFuncionamentoService.alterarHorarioFuncionamento(getCadastroUUID(), request.toDTO())
        return ResponseEntity.ok().build()
    }

    @ApiImplicitParams(ApiImplicitParam(name = "token", value = "Token", required = true, paramType = "header"))
    @PutMapping(value = ["/horarios/funcionamento/alterar"])
    @PreAuthorize("hasAuthority('${ALTERAR_PESSOA_JURIDICA}')")
    fun alterarHorariosFuncionamento(@RequestBody request: List<HorarioFuncionamentoRequest>): ResponseEntity<Void> {
        request.forEach { r -> horarioFuncionamentoService.alterarHorarioFuncionamento(getCadastroUUID(), r.toDTO()) }
        return ResponseEntity.ok().build()
    }

    @ApiImplicitParams(ApiImplicitParam(name = "token", value = "Token", required = true, paramType = "header"))
    @GetMapping(value = ["/horarios/diferenciado"])
    @PreAuthorize("hasAuthority('${ALTERAR_PESSOA_JURIDICA}')")
    fun buscarHorariosDiferenciado(@ApiParam(required = false) @RequestParam("data_cadastro") dataCadastro: String?,
                                   @ApiParam(required = false) @RequestParam("data_especial_inicio") dataEspecialInicio: String?,
                                   @ApiParam(required = false) @RequestParam("data_especial_fim") dataEspecialFim: String?)
            : ResponseEntity<List<HorarioDiferenciadoResponse>> {
        val filtro = FiltroHorarioDiferenciadoDTO(dataCadastro, dataEspecialInicio, dataEspecialFim)
        val horarios = horarioFuncionamentoService.buscarHorariosDiferenciado(getCadastroUUID(), filtro)
        return ResponseEntity.ok(horarios.hdToResponse())
    }

    @ApiImplicitParams(ApiImplicitParam(name = "token", value = "Token", required = true, paramType = "header"))
    @PostMapping(value = ["/horarios/diferenciado"])
    @PreAuthorize("hasAuthority('${ALTERAR_PESSOA_JURIDICA}')")
    fun adicionarHorarioDiferenciado(@RequestBody request: HorarioDiferenciadoRequest): ResponseEntity<List<HorarioDiferenciadoResponse>> {
        val dto = AdicionarHorarioDiferenciadoDTO(request.dataEspecial, request.abertura, request.fechamento)
        horarioFuncionamentoService.adicionarHorarioDiferenciado(getCadastroUUID(), dto)

        val filtro = FiltroHorarioDiferenciadoDTO(null, null, null)
        val hd = horarioFuncionamentoService.buscarHorariosDiferenciado(getCadastroUUID(), filtro)
        return ResponseEntity.ok(hd.hdToResponse())
    }

    @ApiImplicitParams(ApiImplicitParam(name = "token", value = "Token", required = true, paramType = "header"))
    @DeleteMapping(value = ["/horarios/diferenciado/{id_horario}"])
    @PreAuthorize("hasAuthority('${ALTERAR_PESSOA_JURIDICA}')")
    fun removerHorarioDiferenciado(@PathVariable(value = "id_horario") idHorario: Long): ResponseEntity<Void> {
        horarioFuncionamentoService.removerHorarioDiferenciado(idHorario)
        return ResponseEntity.ok().build()
    }

    private fun getCadastroUUID(): String {
        return AuthenticationUtil.getCadastroUUID()!!
    }

    private fun HorarioFuncionamentoRequest.toDTO() =
            AlterarHorarioFuncionamentoDTO(this.dia, this.abertura, this.fechamento, this.fechado)

    private fun List<HorarioFuncionamentoDTO>.toResponse() = this.map { hf -> hf.toResponse() }

    private fun HorarioFuncionamentoDTO.toResponse() =
            HorarioFuncionamentoResponse(this.id, this.dia, this.abertura, this.fechamento, this.fechado)

    private fun List<HorarioDiferenciadoDTO>.hdToResponse() = this.map { hd -> hd.toResponse() }

    private fun HorarioDiferenciadoDTO.toResponse() =
            HorarioDiferenciadoResponse(this.id, this.dataCadastro, this.dataEspecial, this.abertura, this.fechamento)

}