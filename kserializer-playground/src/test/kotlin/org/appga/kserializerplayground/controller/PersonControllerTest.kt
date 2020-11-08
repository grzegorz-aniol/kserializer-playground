package org.appga.kserializerplayground.controller

import kotlinx.serialization.json.Json
import org.appga.kserializerplayground.model.Person
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

//@ExtendWith(SpringExtension::class)
//@WebMvcTest(controllers = [PersonController::class])
//@ContextConfiguration(classes = [WebConfiguration::class])
@SpringBootTest
@AutoConfigureMockMvc
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
        mockMvc.perform(get("$URL/1"))
            .andExpect(status().isOk())
    }

    @Test
    fun `when GET then all persons should be returned`() {
        mockMvc.perform(get(URL))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.[0].email", `is`("user1@domain.pl")))
    }
}