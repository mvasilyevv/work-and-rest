package com.mvasilyev.workandrest.service.person

import com.mvasilyev.workandrest.model.Person

interface PersonService {
    fun findAll(): List<Person>
    fun findById(id: Long): Person?
    fun findByEmail(email: String): Person?
    fun save(person: Person)
    fun update(updatedPerson: Person, id: Long)
    fun delete(person: Person)
}