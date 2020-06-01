package org.frisp.oss.mailadmin.mapper

import org.frisp.oss.mailadmin.dto.AccountDTO
import org.frisp.oss.mailadmin.model.Account
import org.frisp.oss.mailadmin.service.DomainService
import org.springframework.stereotype.Component

@Component
class AccountMapper(
        private val domainService: DomainService,
        private val domainMapper: DomainMapper
) {
    fun toDTO(model: Account): AccountDTO {
        return AccountDTO(
                username = model.username,
                domain = domainMapper.toDTO(domainService.readOne(model.domain)),
                quota = model.quota,
                enabled = model.enabled,
                sendonly = model.sendonly,
                accepted = model.accepted
        )
    }
}
