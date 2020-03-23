package br.com.unip.cadastro.mapper

interface IMapper<E, T> {

    fun map(objeto: E): T
}