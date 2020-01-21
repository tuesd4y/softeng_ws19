package me.tuesd4y.backend.controllers

import org.springframework.data.rest.webmvc.BasePathAwareController
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

import DataStorage
import me.tuesd4y.api.entities.User
import me.tuesd4y.backend.data.repositories.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody

@BasePathAwareController
@RequestMapping("login")
class LoginController(val userRepository: UserRepository) {

    data class LoginData(val username: String, val password: String)

    private final val ds = DataStorage()

    init {
        ds.store("Mustermann Max", "0111", "dfgFSF67#")
        ds.store("HuberKarl", "012222", "HUIkjk24.")
        ds.store("SchmidManfred", "0177777", "778+hghgWE")
        ds.store("Mustermann Max", "01555", "gghhR..REF23")
        ds.store("MüllerBerta", "01444", "f#erDF23.")
        ds.store("test", "test", "test")
    }

    @PostMapping
    fun tryLogin(@RequestBody loginData: LoginData): ResponseEntity<User> {
        val user = ds.findUser(loginData.username, loginData.password)

        // for demo: return a user from the database
        val userFromDb = userRepository.findAll().firstOrNull()

        return if(user == null || userFromDb == null) {
            ResponseEntity.notFound().build()
        } else ResponseEntity.ok(userFromDb)
    }
}
