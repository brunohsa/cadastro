package br.com.unip.cadastro.webservice.model.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
class PessoaFisicaResponse(@JsonProperty(value = "nome") val nome: String?,
                           @JsonProperty(value = "sobrenome") val sobrenome: String?,
                           @JsonProperty(value = "telefone") val telefone: String?,
                           @JsonProperty(value = "cpf") val cpf: String?) : IPessoaResponse