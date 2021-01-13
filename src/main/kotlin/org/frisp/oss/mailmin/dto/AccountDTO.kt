package org.frisp.oss.mailmin.dto

import java.util.*

data class AccountDTO(
        var uuid: UUID,
        var username: String,
        var domain: DomainDTO,
        var quota: Int,
        var enabled: Boolean,
        var sendonly: Boolean,
        var accepted: Boolean
)
