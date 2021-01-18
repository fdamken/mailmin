package org.frisp.oss.mailmin.database.repository

import org.frisp.oss.mailmin.database.model.Account
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AccountRepository : CrudRepository<Account, UUID> {
    fun findByUsername(owner: String): Iterable<Account>
}
