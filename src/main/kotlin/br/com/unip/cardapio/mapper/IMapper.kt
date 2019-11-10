package br.com.unip.cardapio.mapper

interface IMapper<E, T> {

    fun map(objeto: E): T
}