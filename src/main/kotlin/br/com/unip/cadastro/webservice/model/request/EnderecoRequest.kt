package br.com.unip.cadastro.webservice.model.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class EnderecoRequest(@JsonProperty("cep") var cep: String?,
                      @JsonProperty("bairro") val bairro: String?,
                      @JsonProperty("cidade") var cidade: String?,
                      @JsonProperty("estado") var estado: String?,
                      @JsonProperty("logradouro") var logradouro: String?,
                      @JsonProperty("numero") var numero: String?)