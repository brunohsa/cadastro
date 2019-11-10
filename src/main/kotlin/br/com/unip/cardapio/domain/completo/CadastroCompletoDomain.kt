package br.com.unip.cardapio.domain.completo

import br.com.unip.cardapio.repository.entity.enums.EStatusCadastro

class CadastroCompletoDomain(val pessoa: IPessoaCompletaDomain,
                             val status: EStatusCadastro)