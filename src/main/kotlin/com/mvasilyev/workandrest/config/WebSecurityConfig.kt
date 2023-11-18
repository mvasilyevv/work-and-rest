package com.mvasilyev.workandrest.config

import com.mvasilyev.workandrest.model.RoleName
import com.mvasilyev.workandrest.security.PersonDetails
import com.mvasilyev.workandrest.service.person.PersonService
import com.mvasilyev.workandrest.service.person.impl.PersonDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


@Configuration
@EnableWebSecurity
class WebSecurityConfig(
    private val successPersonHandler: SuccessPersonHandler,
    private val personDetailsService: PersonDetailsService
): WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/admin/**").hasAuthority(RoleName.ROLE_ADMIN.name)
            .antMatchers("/login").permitAll()
            .anyRequest().hasAnyAuthority(
                RoleName.ROLE_ADMIN.name,
                RoleName.ROLE_USER.name
            )
            .and()
            .exceptionHandling().accessDeniedPage("/forbidden")
            .and()
            .formLogin()
            .loginPage("/login")
            .usernameParameter("email")
            .loginProcessingUrl("/process_login")
            .successHandler(successPersonHandler)
            .failureUrl("/login")
            .permitAll()
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login")
    }

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(personDetailsService).passwordEncoder(getPasswordEncoder())
    }

    @Bean
    fun getPasswordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}