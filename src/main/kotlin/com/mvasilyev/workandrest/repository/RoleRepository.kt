package com.mvasilyev.workandrest.repository

import com.mvasilyev.workandrest.model.Role
import com.mvasilyev.workandrest.model.RoleName
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository: JpaRepository<Role, Long> {
    fun findByRoleName(roleName: RoleName): Role?
}