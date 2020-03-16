package br.com.unip.cadastro.domain

import br.com.unip.cadastro.repository.entity.enums.EStatusCadastro

class CadastroDomain(val pessoa: IPessoaDomain,
                     val status: EStatusCadastro)