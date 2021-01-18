package org.frisp.oss.mailmin.mapper

import org.frisp.oss.mailmin.dto.LdapUserDTO
import org.frisp.oss.mailmin.ldap.model.LdapUser
import org.springframework.stereotype.Component

@Component
class LdapUserMapper {
    fun toDTO(model: LdapUser): LdapUserDTO {
        return LdapUserDTO(
                dn = model.dn.toString(),
                uid = model.uid,
                mail = model.mail
        )
    }
}
