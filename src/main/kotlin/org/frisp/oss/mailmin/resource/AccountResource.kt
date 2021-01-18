package org.frisp.oss.mailmin.resource

import org.frisp.oss.mailmin.dto.AccountCreationDTO
import org.frisp.oss.mailmin.dto.AccountDTO
import org.frisp.oss.mailmin.mapper.AccountMapper
import org.frisp.oss.mailmin.service.AccountService
import org.frisp.oss.mailmin.util.SecurityConstants
import org.frisp.oss.mailmin.util.SecurityUtil
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/api/v1/accounts")
class AccountResource(
        private val accountService: AccountService,
        private val accountMapper: AccountMapper
) {
    @PutMapping
    @PreAuthorize("#dto.username == @securityUtil.getUserName() || hasRole('${SecurityConstants.ROLE_ADMIN}')")
    fun create(@RequestBody dto: AccountCreationDTO): AccountDTO {
        return accountMapper.toDTO(accountService.create(SecurityUtil.isAdmin(), dto.username, dto.domain, dto.quota, dto.sendonly))
    }

    @GetMapping
    fun readFiltered(): Set<AccountDTO> {
        return accountService.readFiltered(SecurityUtil.getUserName(), SecurityUtil.isAdmin()).map(accountMapper::toDTO).toSet()
    }

    @PostMapping("/{id}/sendonly")
    @PreAuthorize("@accountService.isCurrentUserOwner(#id) || hasRole('${SecurityConstants.ROLE_ADMIN}')")
    @ResponseStatus(HttpStatus.CREATED)
    fun enableSendonly(@PathVariable id: UUID): AccountDTO {
        return accountMapper.toDTO(accountService.setSendonly(id, true))
    }

    @DeleteMapping("/{id}/sendonly")
    @PreAuthorize("@accountService.isCurrentUserOwner(#id) || hasRole('${SecurityConstants.ROLE_ADMIN}')")
    @ResponseStatus(HttpStatus.CREATED)
    fun disableSendonly(@PathVariable id: UUID): AccountDTO {
        return accountMapper.toDTO(accountService.setSendonly(id, false))
    }

    @PostMapping("/{id}/enabled")
    @PreAuthorize("@accountService.isCurrentUserOwner(#id) || hasRole('${SecurityConstants.ROLE_ADMIN}')")
    @ResponseStatus(HttpStatus.CREATED)
    fun enable(@PathVariable id: UUID): AccountDTO {
        return accountMapper.toDTO(accountService.setEnabled(id, true))
    }

    @DeleteMapping("/{id}/enabled")
    @PreAuthorize("@accountService.isCurrentUserOwner(#id) || hasRole('${SecurityConstants.ROLE_ADMIN}')")
    @ResponseStatus(HttpStatus.CREATED)
    fun disable(@PathVariable id: UUID): AccountDTO {
        return accountMapper.toDTO(accountService.setEnabled(id, false))
    }

    @PostMapping("/{id}/accepted")
    @PreAuthorize(SecurityConstants.CHECK_ROLE_ADMIN)
    @ResponseStatus(HttpStatus.CREATED)
    fun accept(@PathVariable id: UUID): AccountDTO {
        return accountMapper.toDTO(accountService.setAccepted(id, true))
    }

    @DeleteMapping("/{id}/accepted")
    @PreAuthorize(SecurityConstants.CHECK_ROLE_ADMIN)
    @ResponseStatus(HttpStatus.CREATED)
    fun unaccept(@PathVariable id: UUID): AccountDTO {
        return accountMapper.toDTO(accountService.setAccepted(id, false))
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@accountService.isCurrentUserOwner(#id) || hasRole('${SecurityConstants.ROLE_ADMIN}')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: UUID) {
        accountService.deleteOne(id)
    }
}
