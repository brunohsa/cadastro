package br.com.unip.cadastro.service

import br.com.unip.cadastro.dto.CadastroDTO
import br.com.unip.cadastro.dto.PessoaJuridicaDTO
import org.springframework.core.io.InputStreamResource

interface IPessoaJuridicaService {

    fun cadastrar(dto: PessoaJuridicaDTO): String

    fun buscarCadastro(uuid: String): CadastroDTO

    fun alterarImagem(cadastroUUID: String, imagemBase64: String)

    fun downloadImagem(cadastroUUID: String): InputStreamResource
}