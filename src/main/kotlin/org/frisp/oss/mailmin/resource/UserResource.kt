package org.frisp.oss.mailmin.resource

import org.frisp.oss.mailmin.dto.LdapUserDTO
import org.frisp.oss.mailmin.dto.UserDTO
import org.frisp.oss.mailmin.mapper.LdapUserMapper
import org.frisp.oss.mailmin.service.LdapUserService
import org.frisp.oss.mailmin.util.SecurityConstants
import org.frisp.oss.mailmin.util.SecurityUtil
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/users")
class UserResource(
        private val ldapUserService: LdapUserService,
        private val ldapUserMapper: LdapUserMapper
) {
    @GetMapping("/current")
    fun read(): UserDTO {
        return UserDTO(SecurityUtil.getUserName(), SecurityUtil.getDisplayName(), SecurityUtil.isAdmin())
    }

    @GetMapping
    @PreAuthorize(SecurityConstants.CHECK_ROLE_ADMIN)
    fun readAllFromLdap(): Set<LdapUserDTO> {
        return ldapUserService.readAll().map(ldapUserMapper::toDTO).toSet()
    }
}
