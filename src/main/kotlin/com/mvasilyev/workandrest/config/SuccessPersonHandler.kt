package com.mvasilyev.workandrest.config

import com.mvasilyev.workandrest.model.RoleName
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class SuccessPersonHandler: AuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val roles = AuthorityUtils.authorityListToSet(authentication.authorities)
        if (roles.contains(RoleName.ROLE_ADMIN.name)) {
            response.sendRedirect("/admin")
        } else {
            response.sendRedirect("/user")
        }
    }

}
