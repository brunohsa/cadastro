package br.com.unip.cardapio.webservice.model.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class CompletarDadosPJRequest(@JsonProperty("telefone") val telefone: String?,
                              @JsonProperty("data_fundacao") val dataFundacao: String?,
                              @JsonProperty("cnpj") val cnpj: String?)