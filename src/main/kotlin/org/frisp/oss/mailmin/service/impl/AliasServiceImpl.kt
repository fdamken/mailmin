package org.frisp.oss.mailmin.service.impl

import org.frisp.oss.mailmin.database.model.Alias
import org.frisp.oss.mailmin.database.repository.AliasRepository
import org.frisp.oss.mailmin.service.AliasService
import org.frisp.oss.mailmin.service.DomainService
import org.frisp.oss.mailmin.util.SecurityUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service("aliasService")
class AliasServiceImpl(
        private val aliasRepository: AliasRepository,
        private val domainService: DomainService
) : AbstractBaseService<Alias, UUID>(), AliasService {
    @Transactional
    override fun create(owner: String, admin: Boolean, sourceUsername: String, sourceDomain: String, destUsername: String, destDomain: String): Alias {
        // Check if the source domain exists.
        domainService.readOne(sourceDomain)

        val alias = Alias(UUID.randomUUID(), owner, sourceUsername, sourceDomain, destUsername, destDomain, false, admin)
        return aliasRepository.save(alias)
    }

    @Transactional(readOnly = true)
    override fun readFiltered(user: String, admin: Boolean): Set<Alias> {
        if (admin) {
            return readAll()
        }
        return getRepository().findByOwner(user).toSet()
    }

    @Transactional
    override fun setEnabled(uuid: UUID, enabled: Boolean): Alias {
        val alias = readOne(uuid)
        alias.enabled = enabled
        return getRepository().save(alias)
    }

    @Transactional
    override fun setAccepted(uuid: UUID, accepted: Boolean): Alias {
        val alias = readOne(uuid)
        alias.accepted = accepted
        return getRepository().save(alias)
    }

    @Transactional(readOnly = true)
    override fun isCurrentUserOwner(uuid: UUID): Boolean {
        return readOne(uuid).owner.equals(SecurityUtil.getUserName(), ignoreCase = true)
    }

    override fun getRepository() = aliasRepository

    override fun getEntityType() = Alias::class
}
