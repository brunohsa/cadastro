package br.com.unip.cadastro.domain

import br.com.unip.cadastro.domain.campos.CampoObrigatorio
import br.com.unip.cadastro.domain.campos.CampoOpcional
import br.com.unip.cadastro.domain.campos.endereco.Bairro
import br.com.unip.cadastro.domain.campos.endereco.Cidade
import br.com.unip.cadastro.domain.campos.endereco.CodigoPostal
import br.com.unip.cadastro.domain.campos.endereco.Estado
import br.com.unip.cadastro.domain.campos.endereco.Logradouro
import br.com.unip.cadastro.domain.campos.endereco.Numero

class EnderecoDomain {

    val cep: CodigoPostal
    val bairro: Bairro
    val cidade: Cidade
    val estado: Estado
    val logradouro: Logradouro
    val numero: Numero
    val latitude: CampoObrigatorio<Double>
    val longitude: CampoObrigatorio<Double>

    constructor(cep: String?, bairro: String?, cidade: String?, estado: String?, logradouro: String?,
                numero: String?, latitude: Double, longitude: Double) {
        this.cep = CodigoPostal(cep)
        this.bairro = Bairro(bairro)
        this.cidade = Cidade(cidade)
        this.estado = Estado(estado)
        this.logradouro = Logradouro(logradouro)
        this.numero = Numero(numero)
        this.latitude = CampoObrigatorio(latitude)
        this.longitude = CampoObrigatorio(longitude)
    }
}