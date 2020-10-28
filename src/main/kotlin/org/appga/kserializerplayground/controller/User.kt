package org.appga.kserializerplayground.controller

import kotlinx.serialization.Serializable

@Serializable
data class User(
	val login: String,
	val email: String,
	val internal: Boolean = true,
	val identity: AuthenticationIdentity,
	val roles: List<String>)

@Serializable
data class AuthenticationIdentity(
	val serviceId: String
)
