package org.frisp.oss.mailadmin.dto

data class AliasDTO(
        var sourceUsername: String,
        var sourceDomain: DomainDTO,
        var destinationUsername: String,
        var destinationDomain: String,
        var enabled: Boolean,
        var accepted: Boolean
)
