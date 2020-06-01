package org.frisp.oss.mailadmin.dto

data class AccountDTO(
        var username: String,
        var domain: DomainDTO,
        var quota: Int,
        var enabled: Boolean,
        var sendonly: Boolean,
        var accepted: Boolean
)
