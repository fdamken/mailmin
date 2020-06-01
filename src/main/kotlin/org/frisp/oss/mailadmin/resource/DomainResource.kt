package org.frisp.oss.mailadmin.resource

import org.frisp.oss.mailadmin.dto.DomainDTO
import org.frisp.oss.mailadmin.mapper.DomainMapper
import org.frisp.oss.mailadmin.service.DomainService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/domains")
class DomainResource(
        private val domainService: DomainService,
        private val domainMapper: DomainMapper
) {
    @GetMapping
    fun readAll(): Set<DomainDTO> {
        return domainService.readAll().map(domainMapper::toDTO).toSet()
    }
}
