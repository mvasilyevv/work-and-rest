package com.mvasilyev.workandrest.model


import org.springframework.security.core.GrantedAuthority
import javax.persistence.*


@Entity
@Table
data class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    val id: Long? = null,

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    val roleName: RoleName
) : GrantedAuthority {
    override fun getAuthority() = roleName.name
}

enum class RoleName {
    ROLE_USER,
    ROLE_ADMIN
}
