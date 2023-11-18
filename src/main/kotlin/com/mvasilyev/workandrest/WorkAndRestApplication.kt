package com.mvasilyev.workandrest

import com.mvasilyev.workandrest.config.DataInitialization
import com.mvasilyev.workandrest.model.RoleName
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WorkAndRestApplication

fun main(args: Array<String>) {
	val context = runApplication<WorkAndRestApplication>(*args)
	context.getBean(DataInitialization::class.java).addPersonsWithRole(
		roleName = RoleName.ROLE_ADMIN
	)
	context.getBean(DataInitialization::class.java).addPersonsWithRole(
		count = 5,
		roleName = RoleName.ROLE_USER)
}
