package org.appga.kserializerplayground.controller

import mu.KotlinLogging
import org.appga.kserializerplayground.model.Person
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
@RequestMapping("/api/v1/persons")
class PersonController {

    val logger = KotlinLogging.logger {}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createNewPerson(@RequestBody person: Person) {
        logger.info { "Person is created: $person" }
    }

    @GetMapping("/{id}")
    fun getPersonById(@PathVariable("id") id: Long): ResponseEntity<Person> =
        ResponseEntity.ok(Person(id = id, email = "email@domain.pl", firstName = "Firstname", lastName = "Lastname"))

    @GetMapping
    fun getAllPersons(): ResponseEntity<List<Person>> =
        ResponseEntity.ok(listOf(
            Person(id = 1, email = "user1@domain.pl", firstName = "Name1", lastName = "Lastname1"),
            Person(id = 2, email = "user2@domain.pl", firstName = "Name2", lastName = "Lastname2")
        ))
}