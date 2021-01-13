package org.frisp.oss.mailmin.util

import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken
import org.keycloak.representations.IDToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder

object SecurityUtil {
    fun getUserName() = getIDToken().preferredUsername

    fun getDisplayName() = getIDToken().name

    fun isAdmin() = hasRole(SecurityContextHolder.getContext().authentication, SecurityConstants.ROLE_ADMIN)

    fun hasRole(auth: Authentication, role: String) = auth.authorities.any { authority -> authority.authority.equals(role, ignoreCase = true) }

    private fun getIDToken(): IDToken {
        val authToken = SecurityContextHolder.getContext().authentication as KeycloakAuthenticationToken
        val account = authToken.details as SimpleKeycloakAccount
        return account.keycloakSecurityContext.idToken
    }
}
