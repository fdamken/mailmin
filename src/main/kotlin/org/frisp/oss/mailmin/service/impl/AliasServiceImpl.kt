package org.frisp.oss.mailmin.service.impl

import org.frisp.oss.mailmin.model.Alias
import org.frisp.oss.mailmin.repository.AliasRepository
import org.frisp.oss.mailmin.service.AliasService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AliasServiceImpl(
        private val aliasRepository: AliasRepository
) : AbstractBaseService<Alias, Int>(), AliasService {
    @Transactional(readOnly = true)
    override fun readFiltered(user: String, admin: Boolean): Set<Alias> {
        if (admin) {
            return readAll()
        }
        return getRepository().findByOwner(user).toSet()
    }

    override fun getRepository() = aliasRepository

    override fun getEntityType() = Alias::class
}
