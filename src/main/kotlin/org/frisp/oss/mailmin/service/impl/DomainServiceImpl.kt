package org.frisp.oss.mailmin.service.impl

import org.frisp.oss.mailmin.model.Domain
import org.frisp.oss.mailmin.repository.DomainRepository
import org.frisp.oss.mailmin.service.DomainService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DomainServiceImpl(
        private val domainRepository: DomainRepository
) : AbstractBaseService<Domain, String>(), DomainService {
    override fun getRepository() = domainRepository

    override fun getEntityType() = Domain::class

    @Transactional
    override fun create(domain: String): Domain {
        return domainRepository.save(Domain(domain))
    }
}
