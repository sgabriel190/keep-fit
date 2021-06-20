package com.example.user_service.persistence.entities

import com.example.user_service.persistence.models.UserModel
import lombok.Data
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.Entity
import javax.persistence.Id

@Entity
@Data
data class UserEntity(
    @Id var id: Int = 0,
    var userName: String,
    var passWord: String,
    var email: String,
    var idUserDetails: Int? = null
): UserDetails{
    fun getRoles(): List<String> {
        return listOf("user")
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority("ROLE_USER"))
    }

    override fun getPassword(): String {
        return passWord
    }

    override fun getUsername(): String {
        return userName
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
}


fun UserEntity.toUserModel() = UserModel(
    id = this.id,
    username = this.userName,
    email = this.email,
    idUserDetails = this.idUserDetails,
)