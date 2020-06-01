package org.frisp.oss.mailadmin.service.impl

import org.frisp.oss.mailadmin.model.Domain
import org.frisp.oss.mailadmin.repository.DomainRepository
import org.frisp.oss.mailadmin.service.DomainService
import org.springframework.stereotype.Service

@Service
class DomainServiceImpl(
        private val domainRepository: DomainRepository
) : AbstractBaseService<Domain, String>(), DomainService {
    override fun getRepository() = domainRepository

    override fun getEntityType() = Domain::class
}
