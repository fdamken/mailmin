package org.frisp.oss.mailmin.resource

import org.frisp.oss.mailmin.dto.DomainDTO
import org.frisp.oss.mailmin.mapper.DomainMapper
import org.frisp.oss.mailmin.service.DomainService
import org.frisp.oss.mailmin.util.SecurityConstants
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*


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

    @PreAuthorize(SecurityConstants.CHECK_ROLE_ADMIN)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteOne(@PathVariable id: String) {
        domainService.deleteOne(id)
    }
}
