package org.frisp.oss.mailmin.repository

import org.frisp.oss.mailmin.model.Alias
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AliasRepository : CrudRepository<Alias, Int> {
    fun findByOwner(owner: String): Iterable<Alias>
}
