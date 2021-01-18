package org.frisp.oss.mailmin.service

import org.frisp.oss.mailmin.database.model.Account
import java.util.*

interface AccountService : BaseService<Account, UUID> {
    fun create(admin: Boolean, username: String, domain: String, quota: Int, sendonly: Boolean): Account

    fun readFiltered(user: String, admin: Boolean): Set<Account>
}
