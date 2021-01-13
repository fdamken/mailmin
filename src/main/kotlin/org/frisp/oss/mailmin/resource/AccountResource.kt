package org.frisp.oss.mailmin.resource

import org.frisp.oss.mailmin.dto.AccountDTO
import org.frisp.oss.mailmin.mapper.AccountMapper
import org.frisp.oss.mailmin.service.AccountService
import org.frisp.oss.mailmin.util.SecurityUtil
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/accounts")
class AccountResource(
        private val accountService: AccountService,
        private val accountMapper: AccountMapper
) {
    @GetMapping
    fun readAll(): Set<AccountDTO> {
        return accountService.readFiltered(SecurityUtil.getUserName(), SecurityUtil.isAdmin()).map(accountMapper::toDTO).toSet()
    }
}
