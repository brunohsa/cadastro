package br.com.unip.cadastro.webservice.model.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class PessoaFisicaRequest(@JsonProperty("nome") val nome: String?,
                          @JsonProperty("sobrenome") var sobrenome: String? = "",
                          @JsonProperty("telefone") val telefone: String?,
                          @JsonProperty("cpf") val cpf: String?)