package br.com.unip.cadastro.webservice.model.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class EnderecoResponse(@JsonProperty(value = "cep") var cep: String?,
                       @JsonProperty(value = "bairro") val bairro: String?,
                       @JsonProperty(value = "cidade") var cidade: String?,
                       @JsonProperty(value = "estado") var estado: String?,
                       @JsonProperty(value = "logradouro") var logradouro: String?,
                       @JsonProperty(value = "numero") var numero: String?)