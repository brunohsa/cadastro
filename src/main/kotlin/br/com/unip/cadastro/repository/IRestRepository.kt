package br.com.unip.cadastro.repository

import kotlin.reflect.KClass

interface IRestRepository {

    fun <T : Any> post(uri: String, request: Any, response: KClass<T>): T

    fun post(uri: String, request: Any): String

    fun <T : Any> get(uri: String, response: KClass<T>): T
}