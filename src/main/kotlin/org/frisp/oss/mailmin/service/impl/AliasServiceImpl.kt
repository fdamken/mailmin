package org.frisp.oss.mailmin.service.impl

import org.frisp.oss.mailmin.model.Alias
import org.frisp.oss.mailmin.repository.AliasRepository
import org.frisp.oss.mailmin.service.AliasService
import org.frisp.oss.mailmin.service.DomainService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class AliasServiceImpl(
        private val aliasRepository: AliasRepository,
        private val domainService: DomainService
) : AbstractBaseService<Alias, Int>(), AliasService {
    @Transactional
    override fun create(owner: String, isAdmin: Boolean, sourceUsername: String, sourceDomain: String, destUsername: String, destDomain: String): Alias {
        // Check if the source domain exists.
        domainService.readOne(sourceDomain)

        val alias = Alias(UUID.randomUUID(), owner, sourceUsername, sourceDomain, destUsername, destDomain, false, false)
        return aliasRepository.save(alias)
    }

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
