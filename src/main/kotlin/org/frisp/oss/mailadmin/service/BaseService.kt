package org.frisp.oss.mailadmin.service

interface BaseService<T, ID> {
    fun readAll(): Set<T>

    fun readOne(id: ID): T
}
