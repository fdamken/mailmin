package org.frisp.oss.mailmin.repository

import org.frisp.oss.mailmin.model.Account
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : CrudRepository<Account, Int>
