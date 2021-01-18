package org.frisp.oss.mailmin.service.impl

import org.frisp.oss.mailmin.database.model.Account
import org.frisp.oss.mailmin.database.repository.AccountRepository
import org.frisp.oss.mailmin.service.AccountService
import org.frisp.oss.mailmin.service.DomainService
import org.frisp.oss.mailmin.util.SecurityUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service("accountService")
class AccountServiceImpl(
        private val accountRepository: AccountRepository,
        private val domainService: DomainService
) : AbstractBaseService<Account, UUID>(), AccountService {
    @Transactional
    override fun create(admin: Boolean, username: String, domain: String, quota: Int, sendonly: Boolean): Account {
        if (quota < 0) {
            throw IllegalArgumentException("quota must not be negative!")
        }

        // Check if the domain exists.
        domainService.readOne(domain)

        val account = Account(UUID.randomUUID(), username, domain, quota, sendonly, false, admin)
        return accountRepository.save(account)
    }

    @Transactional(readOnly = true)
    override fun readFiltered(user: String, admin: Boolean): Set<Account> {
        if (admin) {
            return readAll()
        }
        return getRepository().findByUsername(user).toSet()
    }

    @Transactional
    override fun setSendonly(uuid: UUID, sendonly: Boolean): Account {
        val account = readOne(uuid)
        account.sendonly = sendonly
        return getRepository().save(account)
    }

    @Transactional
    override fun setEnabled(uuid: UUID, enabled: Boolean): Account {
        val account = readOne(uuid)
        account.enabled = enabled
        return getRepository().save(account)
    }

    @Transactional
    override fun setAccepted(uuid: UUID, accepted: Boolean): Account {
        val account = readOne(uuid)
        account.accepted = accepted
        return getRepository().save(account)
    }

    @Transactional(readOnly = true)
    override fun isCurrentUserOwner(uuid: UUID): Boolean {
        return readOne(uuid).username.equals(SecurityUtil.getUserName(), ignoreCase = true)
    }

    override fun getRepository() = accountRepository

    override fun getEntityType() = Account::class
}
