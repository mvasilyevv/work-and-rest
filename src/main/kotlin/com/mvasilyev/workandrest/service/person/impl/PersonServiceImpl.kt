package com.mvasilyev.workandrest.service.person.impl

import com.mvasilyev.workandrest.model.Person
import com.mvasilyev.workandrest.repository.PersonRepository
import com.mvasilyev.workandrest.service.person.PersonService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class PersonServiceImpl(
    val personRepository: PersonRepository,
    val passwordEncoder: PasswordEncoder
): PersonService {
    override fun findAll(): List<Person> {
        return personRepository.findAll()
    }

    override fun findById(id: Long): Person? {
        return personRepository.findById(id).orElse(null)
    }

    override fun save(person: Person) {
//        person.password = passwordEncoder.encode(person.password)
        personRepository.save(person)
    }

    override fun update(updatedPerson: Person, id: Long) {
        findById(id)?.let {person ->
            if (person.password != updatedPerson.password) {
                updatedPerson.password = passwordEncoder.encode(
                    person.password
                )
            }
            person.firstName = updatedPerson.firstName
            person.lastName = updatedPerson.lastName
            person.password = updatedPerson.password
            person.age = updatedPerson.age
            person.email = updatedPerson.email
            person.parameters = updatedPerson.parameters
            person.roles = updatedPerson.roles
            personRepository.save(person)
        }
    }

    override fun delete(person: Person) {
        personRepository.delete(person)
    }
}