package br.com.unip.cardapio.webservice.model.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class PessoaJuridicaRequest(@JsonProperty("nome") val nome: String?,
                            @JsonProperty("telefone") val telefone: String?,
                            @JsonProperty("data_fundacao") val dataFundacao: String?,
                            @JsonProperty("cnpj") val cnpj: String?)