package br.com.unip.cardapio.repository.entity

import br.com.unip.cardapio.repository.entity.enums.ETipoDocumento
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity

@Entity
class PessoaJuridica(nome: String,
                     telefone: String,
                     @Column val dataFundacao: LocalDate,
                     tipoDocumento: ETipoDocumento,
                     numero: String) : Pessoa(nome, telefone, tipoDocumento, numero)