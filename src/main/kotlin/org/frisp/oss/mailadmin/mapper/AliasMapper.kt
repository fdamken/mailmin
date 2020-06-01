package org.frisp.oss.mailadmin.mapper

import org.frisp.oss.mailadmin.dto.AliasDTO
import org.frisp.oss.mailadmin.model.Alias
import org.frisp.oss.mailadmin.service.DomainService
import org.springframework.stereotype.Component

@Component
class AliasMapper(
        private val domainService: DomainService,
        private val domainMapper: DomainMapper
) {
    fun toDTO(model: Alias): AliasDTO {
        return AliasDTO(
                sourceUsername = model.sourceUsername,
                sourceDomain = domainMapper.toDTO(domainService.readOne(model.sourceDomain)),
                destinationUsername = model.destinationUsername,
                destinationDomain = model.destinationDomain,
                enabled = model.enabled,
                accepted = model.accepted
        )
    }
}
