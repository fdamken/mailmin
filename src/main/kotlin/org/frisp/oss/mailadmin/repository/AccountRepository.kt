package org.frisp.oss.mailadmin.repository

import org.frisp.oss.mailadmin.model.Account
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : CrudRepository<Account, Int>
