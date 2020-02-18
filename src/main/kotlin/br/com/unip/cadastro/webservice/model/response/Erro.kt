package br.com.unip.cadastro.webservice.model.response

import br.com.unip.cadastro.exception.ECodigoErro
import com.fasterxml.jackson.annotation.JsonProperty

class Erro(@JsonProperty("codigo") val codigoErro: ECodigoErro,
           @JsonProperty("mensagem") val mensagem: String)