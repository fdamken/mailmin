package org.frisp.oss.mailmin.mapper

import org.frisp.oss.mailmin.database.model.Domain
import org.frisp.oss.mailmin.dto.DomainDTO
import org.springframework.stereotype.Component

@Component
class DomainMapper {
    fun toDTO(model: Domain): DomainDTO {
        return DomainDTO(domain = model.domain)
    }
}
