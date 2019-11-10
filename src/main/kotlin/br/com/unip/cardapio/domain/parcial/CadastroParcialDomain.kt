package br.com.unip.cardapio.domain.parcial

import br.com.unip.cardapio.repository.entity.enums.EStatusCadastro

class CadastroParcialDomain(val pessoa: IPessoaParcialDomain,
                            val status: EStatusCadastro)