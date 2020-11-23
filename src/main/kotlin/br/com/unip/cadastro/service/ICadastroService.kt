package br.com.unip.cadastro.service

import br.com.unip.cadastro.dto.CadastroDTO
import br.com.unip.cadastro.dto.EnderecoDTO

interface ICadastroService {

    fun buscar(uuid: String): CadastroDTO

    fun adicionarEndereco(dto: EnderecoDTO)

    fun buscarEndereco(): EnderecoDTO?

    fun alterarCategoria(categoria: String?)

    fun atualizarNota(cadastroUUID: String, nota: Double?)
}