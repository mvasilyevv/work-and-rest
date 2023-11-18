package com.mvasilyev.workandrest.controller

import com.mvasilyev.workandrest.model.Person
import com.mvasilyev.workandrest.security.PersonDetails
import com.mvasilyev.workandrest.service.person.PersonService
import com.mvasilyev.workandrest.service.role.RoleService
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*


@Controller
@RequestMapping("/admin")
class AdminController(
    private val personService: PersonService,
    private val roleService: RoleService
){

    @GetMapping
    fun index(model: Model): String {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val personDetails: PersonDetails = authentication.principal as PersonDetails
        model.addAttribute("currentPerson", personDetails.getPerson())
        model.addAttribute("persons", personService.findAll())
        model.addAttribute("roles", roleService.findAll())
        model.addAttribute("emptyPerson", Person())
        return "admin/index"
    }

    @PatchMapping("/person/{id}/edit")
    fun updateUser(
        @PathVariable("id") id: Long,
        @ModelAttribute person: Person,
        @RequestParam("newPassword") newPassword: String?
    ): String {
        if (newPassword != null) person.password = newPassword
        personService.update(person, id)
        return "redirect:/admin"
    }

    @PostMapping("/person/new")
    fun addNewUser(@ModelAttribute("person") newPerson: Person): String {
        personService.save(newPerson)
        return "redirect:/admin"
    }

    @DeleteMapping("/person/{id}/delete")
    fun delete(@PathVariable("id") id: Long): String {
        personService.findById(id)?.let {
            personService.delete(it)
        }
        return "redirect:/admin"
    }
}