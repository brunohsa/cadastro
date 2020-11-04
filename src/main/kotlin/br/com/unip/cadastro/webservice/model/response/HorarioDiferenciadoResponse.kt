package br.com.unip.cadastro.webservice.model.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

@JsonIgnoreProperties(ignoreUnknown = true)
class HorarioDiferenciadoResponse(@JsonProperty(value = "id") val id: Long?,
                                  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
                                  @JsonProperty(value = "data_cadastro")
                                  val dataCadatro: LocalDate?,
                                  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
                                  @JsonProperty(value = "data_especial")
                                  val dataEspecial: LocalDate?,
                                  @JsonProperty(value = "abertura") val abertura: String?,
                                  @JsonProperty(value = "fechamento") val fechamento: String?)