package br.com.unip.cadastro.webservice.model.response

import br.com.unip.cadastro.repository.entity.enums.EDiaSemana
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class HorarioFuncionamentoRequest(@JsonProperty(value = "dia") val dia: String?,
                                  @JsonProperty(value = "abertura") val abertura: String?,
                                  @JsonProperty(value = "fechamento") val fechamento: String?,
                                  @JsonProperty(value = "fechado") val fechado: Boolean?)