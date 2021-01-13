package org.frisp.oss.mailmin.dto

import java.util.*

data class AliasDTO(
        var uuid: UUID,
        var owner: String,
        var sourceUsername: String,
        var sourceDomain: DomainDTO,
        var destinationUsername: String,
        var destinationDomain: String,
        var enabled: Boolean,
        var accepted: Boolean
)
