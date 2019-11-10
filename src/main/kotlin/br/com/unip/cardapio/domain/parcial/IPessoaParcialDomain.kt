package br.com.unip.cardapio.domain.parcial

import br.com.unip.cardapio.domain.IPessoaDomain

interface IPessoaParcialDomain : IPessoaDomain {

    fun dadosObrigatoriosPreenchidos(): Boolean
}