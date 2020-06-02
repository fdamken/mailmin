package org.frisp.oss.mailmin.service

interface BaseService<T, ID> {
    fun readAll(): Set<T>

    fun readOne(id: ID): T

    fun deleteOne(id: ID)
}
