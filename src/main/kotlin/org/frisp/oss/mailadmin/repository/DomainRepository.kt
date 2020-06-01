package org.frisp.oss.mailadmin.repository

import org.frisp.oss.mailadmin.model.Domain
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DomainRepository : CrudRepository<Domain, String>
