package br.com.unip.cardapio.repository.util

import org.hibernate.proxy.HibernateProxy
import org.hibernate.Hibernate


class PersistenciaUtil {

    companion object {
        fun <T> inicializarERemoverProxy(entity: T): T {
            Hibernate.initialize(entity)
            if (entity is HibernateProxy) {
                return (entity as HibernateProxy).hibernateLazyInitializer.implementation as T
            }
            return entity
        }
    }
}