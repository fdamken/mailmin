package org.frisp.oss.mailadmin.repository

import org.frisp.oss.mailadmin.model.Alias
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AliasRepository : CrudRepository<Alias, Int>
