package com.mvasilyev.workandrest.service.role.impl

import com.mvasilyev.workandrest.model.Role
import com.mvasilyev.workandrest.model.RoleName
import com.mvasilyev.workandrest.repository.RoleRepository
import com.mvasilyev.workandrest.service.role.RoleService
import org.springframework.stereotype.Service

@Service
class RoleServiceImpl(
    val roleRepository: RoleRepository
): RoleService {
    override fun findAll(): List<Role> {
        return roleRepository.findAll()
    }

    override fun findById(id: Long): Role? {
        return roleRepository.findById(id).orElse(null)
    }

    override fun findByRoleName(roleName: RoleName): Role? {
        return roleRepository.findByRoleName(roleName)
    }

}