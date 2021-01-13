package org.frisp.oss.mailmin.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import kotlin.reflect.KClass

@ResponseStatus(HttpStatus.NOT_FOUND)
class NoSuchEntityException(type: KClass<*>, id: String) : Exception("No <${type.simpleName}> with ID <$id> found!")
