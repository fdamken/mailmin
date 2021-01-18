package org.frisp.oss.mailmin.dto

data class AccountCreationDTO(
        var username: String,
        var domain: String,
        var quota: Int,
        var sendonly: Boolean
)
