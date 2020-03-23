package br.com.unip.cadastro.dto

import com.fasterxml.jackson.annotation.JsonProperty

class CoordenadasDTO {

    @JsonProperty("latitude")
    var latitude: Double = 0.0

    @JsonProperty("longitude")
    var longitude: Double = 0.0

    constructor()

    constructor(latitude: Double, longitude: Double) {
        this.latitude = latitude
        this.longitude = longitude
    }
}