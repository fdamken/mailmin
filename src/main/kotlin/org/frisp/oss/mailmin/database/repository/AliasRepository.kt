package org.frisp.oss.mailmin.database.repository

import org.frisp.oss.mailmin.database.model.Alias
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AliasRepository : CrudRepository<Alias, UUID> {
    fun findByOwner(owner: String): Iterable<Alias>
}
