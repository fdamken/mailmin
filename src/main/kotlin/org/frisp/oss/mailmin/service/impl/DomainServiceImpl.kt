package org.frisp.oss.mailmin.service.impl

import org.frisp.oss.mailmin.model.Domain
import org.frisp.oss.mailmin.repository.DomainRepository
import org.frisp.oss.mailmin.service.DomainService
import org.springframework.stereotype.Service

@Service
class DomainServiceImpl(
        private val domainRepository: DomainRepository
) : AbstractBaseService<Domain, String>(), DomainService {
    override fun getRepository() = domainRepository

    override fun getEntityType() = Domain::class
}
