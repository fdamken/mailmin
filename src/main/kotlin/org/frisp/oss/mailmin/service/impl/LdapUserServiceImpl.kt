package org.frisp.oss.mailmin.service.impl

import org.frisp.oss.mailmin.ldap.repository.LdapUserRepository
import org.frisp.oss.mailmin.service.LdapUserService
import org.springframework.stereotype.Service

@Service
class LdapUserServiceImpl(
        private val ldapUserRepository: LdapUserRepository
) : LdapUserService {
    override fun readAll() = ldapUserRepository.findAll().filterNotNull().toSet()
}
