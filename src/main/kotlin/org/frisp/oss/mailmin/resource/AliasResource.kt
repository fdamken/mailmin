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
    fun readAll(): Set<AliasDTO> {
        return aliasService.readFiltered(SecurityUtil.getUserName(), SecurityUtil.isAdmin()).map(aliasMapper::toDTO).toSet()
    }

    @PostMapping("/{uuid}/enabled")
    @PreAuthorize("@aliasService.isCurrentUserOwner(#uuid) || hasRole('${SecurityConstants.ROLE_ADMIN}')")
    @ResponseStatus(HttpStatus.CREATED)
    fun enableAlias(@PathVariable uuid: UUID): AliasDTO {
        return aliasMapper.toDTO(aliasService.setEnabled(uuid, true))
    }

    @DeleteMapping("/{uuid}/enabled")
    @PreAuthorize("@aliasService.isCurrentUserOwner(#uuid) || hasRole('${SecurityConstants.ROLE_ADMIN}')")
    @ResponseStatus(HttpStatus.CREATED)
    fun disableAlias(@PathVariable uuid: UUID): AliasDTO {
        return aliasMapper.toDTO(aliasService.setEnabled(uuid, false))
    }

    @PostMapping("/{uuid}/accepted")
    @PreAuthorize(SecurityConstants.CHECK_ROLE_ADMIN)
    @ResponseStatus(HttpStatus.CREATED)
    fun acceptAlias(@PathVariable uuid: UUID): AliasDTO {
        return aliasMapper.toDTO(aliasService.setAccepted(uuid, true))
    }

    @DeleteMapping("/{uuid}/accepted")
    @PreAuthorize(SecurityConstants.CHECK_ROLE_ADMIN)
    @ResponseStatus(HttpStatus.CREATED)
    fun unacceptAlias(@PathVariable uuid: UUID): AliasDTO {
        return aliasMapper.toDTO(aliasService.setAccepted(uuid, false))
    }
}
