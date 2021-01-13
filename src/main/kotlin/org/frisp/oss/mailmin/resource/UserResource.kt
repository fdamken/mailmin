package org.frisp.oss.mailmin.resource

import org.frisp.oss.mailmin.dto.UserDTO
import org.frisp.oss.mailmin.mapper.AccountMapper
import org.frisp.oss.mailmin.service.AccountService
import org.frisp.oss.mailmin.util.SecurityUtil
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/users")
class UserResource(
        private val accountService: AccountService,
        private val accountMapper: AccountMapper
) {
    @GetMapping("/current")
    fun read(): UserDTO {
        return UserDTO(SecurityUtil.getUserName(), SecurityUtil.getDisplayName(), SecurityUtil.isAdmin())
    }
}
