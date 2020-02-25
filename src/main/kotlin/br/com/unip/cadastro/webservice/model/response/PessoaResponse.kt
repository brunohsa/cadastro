package br.com.unip.cadastro.webservice.model.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
class PessoaResponse(@JsonProperty(value = "nome") val nome: String,
                     @JsonProperty(value = "sobrenome") var sobrenome: String?,
                     @JsonProperty(value = "telefone") var telefone: String?,
                     @JsonProperty(value = "data_nascimento") var dataNascimento: String?)