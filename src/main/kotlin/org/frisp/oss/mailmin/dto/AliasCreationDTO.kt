package org.frisp.oss.mailmin.dto

data class AliasCreationDTO(
        var sourceUsername: String,
        var sourceDomain: String,
        var destinationUsername: String,
        var destinationDomain: String
)
