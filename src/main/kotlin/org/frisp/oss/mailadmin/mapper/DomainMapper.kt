package org.frisp.oss.mailadmin.mapper

import org.frisp.oss.mailadmin.dto.DomainDTO
import org.frisp.oss.mailadmin.model.Domain
import org.springframework.stereotype.Component

@Component
class DomainMapper {
    fun toDTO(model: Domain): DomainDTO {
        return DomainDTO(domain = model.domain)
    }
}
