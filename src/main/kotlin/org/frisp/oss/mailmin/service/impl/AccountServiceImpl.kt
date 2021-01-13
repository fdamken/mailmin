package org.frisp.oss.mailmin.service.impl

import org.frisp.oss.mailmin.model.Account
import org.frisp.oss.mailmin.repository.AccountRepository
import org.frisp.oss.mailmin.service.AccountService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class AccountServiceImpl(
        private val accountRepository: AccountRepository
) : AbstractBaseService<Account, UUID>(), AccountService {
    @Transactional(readOnly = true)
    override fun readFiltered(user: String, admin: Boolean): Set<Account> {
        if (admin) {
            return readAll()
        }
        return getRepository().findByUsername(user).toSet()
    }

    override fun getRepository() = accountRepository

    override fun getEntityType() = Account::class
}
