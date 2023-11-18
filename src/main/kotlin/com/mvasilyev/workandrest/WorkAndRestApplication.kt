package com.mvasilyev.workandrest

import com.mvasilyev.workandrest.config.DataInitialization
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WorkAndRestApplication

fun main(args: Array<String>) {
	val context = runApplication<WorkAndRestApplication>(*args)
	context.getBean(DataInitialization::class.java).addPersons(10)
}
