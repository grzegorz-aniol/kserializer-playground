package org.appga.kserializerplayground.controller

import kotlinx.serialization.json.Json
import org.appga.kserializerplayground.model.AuthenticationIdentity
import org.appga.kserializerplayground.model.User
import org.assertj.core.api.Assertions.assertThat
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
@WebMvcTest(controllers = [UserController::class])
class UserControllerTest {

    private val URL = "/api/v1/users"

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun `when POST to endpoint then new user should be created`() {
        // given
        val user = User(
            login = "gangel",
            email = "gangel@tst.pl",
            internal = false,
            roles = listOf("USER", "ADMIN"),
            identity = AuthenticationIdentity("google")
        )

        // then
        mockMvc.perform(post(URL)
                .content(Json.encodeToString(User.serializer(), user))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
    }

    @Test
    fun `when GET with ID then user should be returned`() {
        // given
        val expectedUser = User(
                login = "userLogin",
                email = "user@domain.com",
                internal = false,
                identity = AuthenticationIdentity(serviceId = "google"),
                roles = listOf("USER", "ADMIN")
        )

        // expect
        val userJson = mockMvc.perform(MockMvcRequestBuilders.get("$URL/1"))
            .andExpect(status().isOk())
            .andReturn()
            .response.contentAsString

        val user = Json.decodeFromString(User.serializer(), userJson)
        assertThat(user).isEqualTo(expectedUser)
    }
}