package com.mvasilyev.workandrest.config

import com.mvasilyev.workandrest.model.Person
import com.mvasilyev.workandrest.model.Role
import com.mvasilyev.workandrest.model.RoleName
import com.mvasilyev.workandrest.repository.RoleRepository
import com.mvasilyev.workandrest.service.person.impl.PersonServiceImpl
import org.springframework.stereotype.Component

@Component
class DataInitialization(
    private val personServiceImpl: PersonServiceImpl,
    private val roleRepository: RoleRepository
) {
    fun addPersons(count: Int) {
        val role = Role(roleName = RoleName.ROLE_USER)
        roleRepository.save(role)
        for(i in 1..count) {
            val parameters = Person.Parameters(
                (170 + i).toDouble(),
                (60 + i).toDouble()
            )
            val person = Person(
                i.toLong(),
                firstName = "FirstName${i}",
                lastName = "LastName${i}",
                password = "password${i}",
               age =  (30 + i),
               email =  "email@${i}",
               roles =  setOf(role),
              parameters = parameters
            )
            personServiceImpl.save(person)
        }
    }
}