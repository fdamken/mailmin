package org.frisp.oss.mailadmin.service.impl

import org.frisp.oss.mailadmin.model.Account
import org.frisp.oss.mailadmin.repository.AccountRepository
import org.frisp.oss.mailadmin.service.AccountService
import org.springframework.stereotype.Service

@Service
class AccountServiceImpl(
        private val accountRepository: AccountRepository
) : AbstractBaseService<Account, Int>(), AccountService {
    override fun getRepository() = accountRepository

    override fun getEntityType() = Account::class
}
