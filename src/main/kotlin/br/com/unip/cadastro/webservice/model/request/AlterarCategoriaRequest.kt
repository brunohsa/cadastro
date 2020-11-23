package br.com.unip.cadastro.webservice.model.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class AlterarCategoriaRequest(@JsonProperty("categoria") val categoria: String?)