package org.frisp.oss.mailmin.service

import org.frisp.oss.mailmin.model.Alias

interface AliasService : BaseService<Alias, Int> {
    fun create(owner: String, isAdmin: Boolean, sourceUsername: String, sourceDomain: String, destUsername: String, destDomain: String): Alias

    fun readFiltered(user: String, admin: Boolean): Set<Alias>
}
