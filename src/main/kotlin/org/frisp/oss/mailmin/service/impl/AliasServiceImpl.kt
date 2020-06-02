package org.frisp.oss.mailmin.service.impl

import org.frisp.oss.mailmin.model.Alias
import org.frisp.oss.mailmin.repository.AliasRepository
import org.frisp.oss.mailmin.service.AliasService
import org.springframework.stereotype.Service

@Service
class AliasServiceImpl(private val aliasRepository: AliasRepository) : AbstractBaseService<Alias, Int>(), AliasService {
    override fun getRepository() = aliasRepository

    override fun getEntityType() = Alias::class
}
