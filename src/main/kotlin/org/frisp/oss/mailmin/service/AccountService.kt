package org.frisp.oss.mailmin.service

import org.frisp.oss.mailmin.model.Account
import java.util.*

interface AccountService : BaseService<Account, UUID> {
    fun readFiltered(user: String, admin: Boolean): Set<Account>
}
