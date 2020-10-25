package br.com.unip.cadastro.webservice.model.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
class PessoaJuridicaResponse(@JsonProperty(value = "razao_social") val razaoSocial: String?,
                             @JsonProperty(value = "nome_fantasia") val nomeFantasia: String?,
                             @JsonProperty(value = "telefone") val telefone: String?,
                             @JsonProperty(value = "cnpj") val cnpj: String?,
                             @JsonProperty(value = "endereco") val endereco: EnderecoResponse?) : IPessoaResponse