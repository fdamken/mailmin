package org.frisp.oss.mailmin.ldap.repository

import org.frisp.oss.mailmin.ldap.model.LdapUser
import org.springframework.data.ldap.repository.LdapRepository
import org.springframework.stereotype.Repository

@Repository
interface LdapUserRepository : LdapRepository<LdapUser>
