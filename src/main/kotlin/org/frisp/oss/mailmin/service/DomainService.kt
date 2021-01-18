package org.frisp.oss.mailmin.service

import org.frisp.oss.mailmin.database.model.Domain

interface DomainService : BaseService<Domain, String> {
    fun create(domain: String): Domain
}
