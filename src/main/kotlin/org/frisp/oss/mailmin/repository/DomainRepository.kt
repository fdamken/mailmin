package org.frisp.oss.mailmin.repository

import org.frisp.oss.mailmin.model.Domain
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DomainRepository : CrudRepository<Domain, String> {
    fun findByOrderByDomain(): Iterable<Domain>
}
