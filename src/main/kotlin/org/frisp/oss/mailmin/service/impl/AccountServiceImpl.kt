package org.frisp.oss.mailmin.service.impl

import org.frisp.oss.mailmin.model.Account
import org.frisp.oss.mailmin.repository.AccountRepository
import org.frisp.oss.mailmin.service.AccountService
import org.springframework.stereotype.Service

@Service
class AccountServiceImpl(
        private val accountRepository: AccountRepository
) : AbstractBaseService<Account, Int>(), AccountService {
    override fun getRepository() = accountRepository

    override fun getEntityType() = Account::class
}