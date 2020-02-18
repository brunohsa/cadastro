package br.com.unip.cadastro.dto

data class EnderecoDTO(var cep: String?,
                       val bairro: String?,
                       var cidade: String?,
                       var estado: String?,
                       var logradouro: String?,
                       var numero: String?)