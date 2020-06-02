package org.frisp.oss.mailmin.mapper

import org.frisp.oss.mailmin.dto.AliasDTO
import org.frisp.oss.mailmin.model.Alias
import org.frisp.oss.mailmin.service.DomainService
import org.springframework.stereotype.Component

@Component
class AliasMapper(
        private val domainService: DomainService,
        private val domainMapper: DomainMapper
) {
    fun toDTO(model: Alias): AliasDTO {
        return AliasDTO(
                id = model.id,
                owner = model.owner,
                sourceUsername = model.sourceUsername,
                sourceDomain = domainMapper.toDTO(domainService.readOne(model.sourceDomain)),
                destinationUsername = model.destinationUsername,
                destinationDomain = model.destinationDomain,
                enabled = model.enabled,
                accepted = model.accepted
        )
    }
}
