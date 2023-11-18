package com.mvasilyev.workandrest.service.role

import com.mvasilyev.workandrest.model.Role
import com.mvasilyev.workandrest.model.RoleName

interface RoleService {
    fun findAll(): List<Role>
    fun findById(id: Long): Role?
    fun findByRoleName(roleName: RoleName): Role?
}