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


@RestController
@RequestMapping("/api/v1/aliases")
class AliasResource(
        private val aliasService: AliasService,
        private val aliasMapper: AliasMapper
) {
    @PreAuthorize(SecurityConstants.CHECK_ROLE_ADMIN)
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
}
