package org.appga.kserializerplayground.controller

import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class UserController {

    val logger = KotlinLogging.logger {}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createNewUser(@RequestBody user: User) {
        logger.info { "User is created: $user" }
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable("id") id: Long): ResponseEntity<User> =
        ResponseEntity.ok(
            User(
                login = "userLogin",
                email = "user@domain.com",
                internal = false,
                identity = AuthenticationIdentity(serviceId = "google"),
                roles = listOf("USER", "ADMIN")
            )
        )

}