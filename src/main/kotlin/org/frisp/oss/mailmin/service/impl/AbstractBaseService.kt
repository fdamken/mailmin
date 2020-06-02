package org.frisp.oss.mailmin.service.impl

import org.frisp.oss.mailmin.exception.NoSuchEntityException
import org.frisp.oss.mailmin.service.BaseService
import org.springframework.data.repository.CrudRepository
import kotlin.reflect.KClass

abstract class AbstractBaseService<T : Any, ID> : BaseService<T, ID> {
    override fun readAll() = getRepository().findAll().toSet()

    override fun readOne(id: ID): T {
        return getRepository().findById(id).orElseThrow { NoSuchEntityException(getEntityType(), id.toString()) }
    }

    override fun deleteOne(id: ID) {
        getRepository().deleteById(id)
    }

    protected abstract fun getRepository(): CrudRepository<T, ID>

    protected abstract fun getEntityType(): KClass<T>
}
