package org.frisp.oss.mailmin.mapper

import org.frisp.oss.mailmin.dto.AccountDTO
import org.frisp.oss.mailmin.database.model.Account
import org.frisp.oss.mailmin.service.DomainService
import org.springframework.stereotype.Component

@Component
class AccountMapper(
        private val domainService: DomainService,
        private val domainMapper: DomainMapper
) {
    fun toDTO(model: Account): AccountDTO {
        return AccountDTO(
                uuid = model.uuid,
                username = model.username,
                domain = domainMapper.toDTO(domainService.readOne(model.domain)),
                quota = model.quota,
                enabled = model.enabled,
                sendonly = model.sendonly,
                accepted = model.accepted
        )
    }
}
