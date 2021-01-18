package org.frisp.oss.mailmin.resource

import org.frisp.oss.mailmin.dto.AliasCreationDTO
import org.frisp.oss.mailmin.dto.AliasDTO
import org.frisp.oss.mailmin.mapper.AliasMapper
import org.frisp.oss.mailmin.service.AliasService
import org.frisp.oss.mailmin.util.SecurityConstants
import org.frisp.oss.mailmin.util.SecurityUtil
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/api/v1/aliases")
class AliasResource(
        private val aliasService: AliasService,
        private val aliasMapper: AliasMapper
) {
    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody dto: AliasCreationDTO): AliasDTO {
        val alias = aliasService.create(SecurityUtil.getUserName(), SecurityUtil.isAdmin(), dto.sourceUsername, dto.sourceDomain, dto.destinationUsername, dto.destinationDomain)
        return aliasMapper.toDTO(alias)
    }

    @GetMapping
    fun readFiltered(): Set<AliasDTO> {
        return aliasService.readFiltered(SecurityUtil.getUserName(), SecurityUtil.isAdmin()).map(aliasMapper::toDTO).toSet()
    }

    @PostMapping("/{id}/enabled")
    @PreAuthorize("@aliasService.isCurrentUserOwner(#id) || hasRole('${SecurityConstants.ROLE_ADMIN}')")
    @ResponseStatus(HttpStatus.CREATED)
    fun enable(@PathVariable id: UUID): AliasDTO {
        return aliasMapper.toDTO(aliasService.setEnabled(id, true))
    }

    @DeleteMapping("/{id}/enabled")
    @PreAuthorize("@aliasService.isCurrentUserOwner(#id) || hasRole('${SecurityConstants.ROLE_ADMIN}')")
    @ResponseStatus(HttpStatus.CREATED)
    fun disable(@PathVariable id: UUID): AliasDTO {
        return aliasMapper.toDTO(aliasService.setEnabled(id, false))
    }

    @PostMapping("/{id}/accepted")
    @PreAuthorize(SecurityConstants.CHECK_ROLE_ADMIN)
    @ResponseStatus(HttpStatus.CREATED)
    fun accept(@PathVariable id: UUID): AliasDTO {
        return aliasMapper.toDTO(aliasService.setAccepted(id, true))
    }

    @DeleteMapping("/{id}/accepted")
    @PreAuthorize(SecurityConstants.CHECK_ROLE_ADMIN)
    @ResponseStatus(HttpStatus.CREATED)
    fun unaccept(@PathVariable id: UUID): AliasDTO {
        return aliasMapper.toDTO(aliasService.setAccepted(id, false))
    }

    @PreAuthorize("@aliasService.isCurrentUserOwner(#id) || hasRole('${SecurityConstants.ROLE_ADMIN}')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteOne(@PathVariable id: UUID) {
        return aliasService.deleteOne(id)
    }
}
