package com.mvasilyev.workandrest.service.person.impl

import com.mvasilyev.workandrest.repository.PersonRepository
import com.mvasilyev.workandrest.security.PersonDetails
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class PersonDetailsService(
    private val personRepository: PersonRepository
): UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val person = personRepository.findByEmail(username) ?: throw UsernameNotFoundException("User not found")
        return PersonDetails(person)
    }
}