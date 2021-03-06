package org.frisp.oss.mailmin.service.impl

import org.frisp.oss.mailmin.database.model.Domain
import org.frisp.oss.mailmin.database.repository.DomainRepository
import org.frisp.oss.mailmin.service.DomainService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DomainServiceImpl(
        private val domainRepository: DomainRepository
) : AbstractBaseService<Domain, String>(), DomainService {
    override fun readAll() = getRepository().findByOrderByDomain().toSet()

    @Transactional
    override fun create(domain: String): Domain {
        return domainRepository.save(Domain(domain))
    }

    override fun getRepository() = domainRepository

    override fun getEntityType() = Domain::class
}
