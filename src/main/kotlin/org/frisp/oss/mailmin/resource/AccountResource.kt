package org.frisp.oss.mailmin.resource

import org.frisp.oss.mailmin.dto.AccountCreationDTO
import org.frisp.oss.mailmin.dto.AccountDTO
import org.frisp.oss.mailmin.mapper.AccountMapper
import org.frisp.oss.mailmin.service.AccountService
import org.frisp.oss.mailmin.util.SecurityUtil
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/accounts")
class AccountResource(
        private val accountService: AccountService,
        private val accountMapper: AccountMapper
) {
    @PutMapping
    fun create(@RequestBody dto: AccountCreationDTO): AccountDTO {
        return accountMapper.toDTO(accountService.create(SecurityUtil.isAdmin(), dto.username, dto.domain, dto.quota, dto.sendonly))
    }

    @GetMapping
    fun readFiltered(): Set<AccountDTO> {
        return accountService.readFiltered(SecurityUtil.getUserName(), SecurityUtil.isAdmin()).map(accountMapper::toDTO).toSet()
    }
}
