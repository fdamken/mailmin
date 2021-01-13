package org.frisp.oss.mailmin.service

import org.frisp.oss.mailmin.model.Account

interface AccountService : BaseService<Account, Int> {
    fun readFiltered(user: String, admin: Boolean): Set<Account>
}
