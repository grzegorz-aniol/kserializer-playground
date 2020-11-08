package org.appga.kserializerplayground.model

import kotlinx.serialization.Serializable

@Serializable
data class Person(
    val id: Long? = null,
    val email: String,
    val firstName: String? = null,
    val lastName: String? = null
)
