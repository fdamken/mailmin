package org.frisp.oss.mailmin.dto

data class LdapUserDTO(
        var dn: String,
        var uid: String,
        var mail: String
)
