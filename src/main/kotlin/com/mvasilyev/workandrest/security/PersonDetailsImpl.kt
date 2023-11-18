package com.mvasilyev.workandrest.security

import com.mvasilyev.workandrest.model.Person
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class PersonDetailsImpl(
    private val person: Person
): UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val grantedAuthority: MutableList<SimpleGrantedAuthority> = ArrayList()
        for (r in person.roles) {
            grantedAuthority.add(SimpleGrantedAuthority(r.getAuthority()))
        }
        return grantedAuthority
    }

    override fun getPassword(): String {
        return person.password
    }

    override fun getUsername(): String {
        return person.email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    fun getPerson(): Person {
        return person
    }
}