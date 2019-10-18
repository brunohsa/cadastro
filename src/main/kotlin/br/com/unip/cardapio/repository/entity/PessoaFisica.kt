package br.com.unip.cardapio.repository.entity

import br.com.unip.cardapio.repository.entity.enums.ETipoDocumento
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity

@Entity
class PessoaFisica(nome: String,
                   @Column val sobrenome: String,
                   telefone: String,
                   @Column val dataNascimento: LocalDate,
                   tipoDocumento: ETipoDocumento,
                   numero: String) : Pessoa(nome, telefone, tipoDocumento, numero)