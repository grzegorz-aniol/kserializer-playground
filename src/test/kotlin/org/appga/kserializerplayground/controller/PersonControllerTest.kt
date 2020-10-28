package org.appga.kserializerplayground.controller

import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@WebMvcTest(controllers = [PersonController::class])
class PersonControllerTest {

    private val URL = "/api/v1/persons"

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun `when POST to endpoint then new person should be created`() {
        // given
        val person = Person(
            email = "gangel@tst.pl",
            firstName = "Greg",
            lastName = "Angel"
        )

        // then
        mockMvc.perform(post(URL)
                .content(Json.encodeToString(Person.serializer(), person))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
    }

    @Test
    fun `when GET with ID then person should be returned`() {
        // expect
        mockMvc.perform(MockMvcRequestBuilders.get("$URL/1"))
            .andExpect(status().isOk())
    }
}