package org.frisp.oss.mailmin.resource

import org.frisp.oss.mailmin.dto.AliasDTO
import org.frisp.oss.mailmin.mapper.AliasMapper
import org.frisp.oss.mailmin.service.AliasService
import org.frisp.oss.mailmin.util.SecurityUtil
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/aliases")
class AliasResource(
        private val aliasService: AliasService,
        private val aliasMapper: AliasMapper
) {
    @GetMapping
    fun readAll(): Set<AliasDTO> {
        return aliasService.readFiltered(SecurityUtil.getUserName(), SecurityUtil.isAdmin()).map(aliasMapper::toDTO).toSet()
    }
}
