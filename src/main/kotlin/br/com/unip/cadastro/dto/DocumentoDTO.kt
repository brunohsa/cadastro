package br.com.unip.cadastro.dto

import br.com.unip.cadastro.repository.entity.enums.ETipoDocumento

data class DocumentoDTO(val tipo: ETipoDocumento,
                        val numero: String)

