package br.com.unip.cadastro.dto

import br.com.unip.cadastro.repository.entity.ECategoria

class CadastroDTO(val uuid: String,
                  val categoria: ECategoria?,
                  val status: String,
                  val pessoa: IPessoaDTO)