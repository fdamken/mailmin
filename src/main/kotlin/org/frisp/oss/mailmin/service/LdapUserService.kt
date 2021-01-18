package org.frisp.oss.mailmin.service

import org.frisp.oss.mailmin.ldap.model.LdapUser

interface LdapUserService {
    fun readAll(): Set<LdapUser>
}
