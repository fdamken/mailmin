package org.frisp.oss.mailmin.dto

data class AliasDTO(
        var id: Int,
        var owner: String,
        var sourceUsername: String,
        var sourceDomain: DomainDTO,
        var destinationUsername: String,
        var destinationDomain: String,
        var enabled: Boolean,
        var accepted: Boolean
)
