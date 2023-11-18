package com.mvasilyev.workandrest.controller

import com.mvasilyev.workandrest.security.PersonDetails
import com.mvasilyev.workandrest.service.person.impl.PersonDetailsService
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/person")
class PersonController {
    @GetMapping
    fun showUser(model: Model): String {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val personDetails: PersonDetails = authentication.principal as PersonDetails
        model.addAttribute("currentPerson", personDetails.getPerson())
        return "person/index"
    }
}