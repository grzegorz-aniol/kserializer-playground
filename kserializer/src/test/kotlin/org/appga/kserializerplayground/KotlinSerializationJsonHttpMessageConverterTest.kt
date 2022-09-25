package org.appga.kserializerplayground

import org.appga.kserializerplayground.model.Person
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.http.converter.json.KotlinSerializationJsonHttpMessageConverter
import org.springframework.mock.http.MockHttpOutputMessage
import kotlin.reflect.jvm.javaType
import kotlin.reflect.typeOf

class KotlinSerializationJsonHttpMessageConverterTest {

	@ExperimentalStdlibApi
	private val converter = KotlinSerializationJsonHttpMessageConverter()

	@OptIn(ExperimentalStdlibApi::class)
	@Test
	fun serializeList() {
		val message = MockHttpOutputMessage()
		val p1 = Person(email = "gan1@test.pl", firstName = "Greg", lastName = "Angel")
		val p2 = Person(email = "gan2@test.pl", firstName = "Greg", lastName = "Angel")
		val list = arrayListOf(p1, p2)
		val kType = typeOf<ArrayList<Person>>()

		converter.write(list, kType.javaType, MediaType.APPLICATION_JSON, message)

		assertThat(converter.canWrite(kType.javaType, kType.javaClass, MediaType.APPLICATION_JSON)).isTrue()
		assertThat(message.bodyAsString).isEqualTo("""
			[{"email":"gan1@test.pl","firstName":"Greg","lastName":"Angel"},{"email":"gan2@test.pl","firstName":"Greg","lastName":"Angel"}]
		""".trimIndent())
	}
}