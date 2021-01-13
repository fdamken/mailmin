package org.frisp.oss.mailmin.service

import org.frisp.oss.mailmin.model.Alias
import java.util.*

interface AliasService : BaseService<Alias, UUID> {
    fun create(owner: String, admin: Boolean, sourceUsername: String, sourceDomain: String, destUsername: String, destDomain: String): Alias

    fun readFiltered(user: String, admin: Boolean): Set<Alias>

    fun setEnabled(uuid: UUID, enabled: Boolean): Alias

    fun setAccepted(uuid: UUID, accepted: Boolean): Alias

    fun isCurrentUserOwner(uuid: UUID): Boolean
}
