package com.mvasilyev.workandrest.repository

import com.mvasilyev.workandrest.model.Person
import org.springframework.data.jpa.repository.JpaRepository

interface PersonRepository: JpaRepository<Person, Long>