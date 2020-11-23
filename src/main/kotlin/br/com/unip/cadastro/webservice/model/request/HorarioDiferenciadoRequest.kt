package br.com.unip.cadastro.webservice.model.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class HorarioDiferenciadoRequest(@JsonProperty("data_especial") val dataEspecial: String?,
                                 @JsonProperty("abertura") val abertura: String?,
                                 @JsonProperty("fechamento") val fechamento: String?)