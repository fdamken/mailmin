package org.frisp.oss.mailadmin.service.impl

import org.frisp.oss.mailadmin.model.Alias
import org.frisp.oss.mailadmin.repository.AliasRepository
import org.frisp.oss.mailadmin.service.AliasService
import org.springframework.stereotype.Service

@Service
class AliasServiceImpl(private val aliasRepository: AliasRepository) : AbstractBaseService<Alias, Int>(), AliasService {
    override fun getRepository() = aliasRepository

    override fun getEntityType() = Alias::class
}
