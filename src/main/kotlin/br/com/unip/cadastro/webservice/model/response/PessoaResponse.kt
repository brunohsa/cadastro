package br.com.unip.cadastro.webservice.model.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class PessoaResponse(@JsonProperty(value = "nome") val nome: String,
                     @JsonProperty(value = "documento") var documento: DocumentoResponse)