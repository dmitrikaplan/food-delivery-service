package com.example.authservice.domain.user

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService

@Entity
@Table(name = "users")
class User(
    private var username: String,
    private var password: String
) : UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    lateinit var firstname: String
    lateinit var lastname: String
    lateinit var email: String
    @Enumerated(EnumType.STRING)
    lateinit var role: Role
    var activated: Boolean = false
    var activationCode: String? = null


    override fun getAuthorities(): List<GrantedAuthority> {
       return listOf(role)
    }

    override fun getPassword(): String {
        return password
    }

    fun setPassword(password: String){
        this.password = password
    }

    override fun getUsername(): String {
        return username
    }

    fun setUsername(username: String){
        this.username = username
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
        return activated
    }
}