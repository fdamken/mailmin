package org.frisp.oss.mailmin.dto

data class AccountDTO(
        var id: Int,
        var username: String,
        var domain: DomainDTO,
        var quota: Int,
        var enabled: Boolean,
        var sendonly: Boolean,
        var accepted: Boolean
)
