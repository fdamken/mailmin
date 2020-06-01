package org.frisp.oss.mailadmin.service.impl

import org.frisp.oss.mailadmin.exception.NoSuchEntityException
import org.frisp.oss.mailadmin.service.BaseService
import org.springframework.data.repository.CrudRepository
import kotlin.reflect.KClass

abstract class AbstractBaseService<T : Any, ID> : BaseService<T, ID> {
    override fun readAll() = getRepository().findAll().toSet()

    override fun readOne(id: ID): T {
        return getRepository().findById(id).orElseThrow { NoSuchEntityException(getEntityType(), id.toString()) }
    }

    protected abstract fun getRepository(): CrudRepository<T, ID>

    protected abstract fun getEntityType(): KClass<T>
}
