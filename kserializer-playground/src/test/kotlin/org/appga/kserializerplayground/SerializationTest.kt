package org.appga.kserializerplayground

import org.appga.kserializerplayground.model.Person
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.http.converter.json.KotlinSerializationJsonHttpMessageConverter
import org.springframework.mock.http.MockHttpOutputMessage
import kotlin.reflect.typeOf

class SerializationTest {

	@ExperimentalStdlibApi
	private val converter = KotlinSerializationJsonHttpMessageConverter()

//			contextual(typeOf<List<Person>>.javaType, ListSerializer(Person.serializer()))
//			val type = typeOf<List<Person>>()
//			contextual(type.javaClass, ListSerializer(Person.serializer()))

	@ExperimentalStdlibApi
	@Test
	fun serializeList() {
//		val ktype = typeOf<List<Person>>()
		val message = MockHttpOutputMessage()
		val p1 = Person(email = "gan1@test.pl", firstName = "Greg", lastName = "Angel")
		val p2 = Person(email = "gan2@test.pl", firstName = "Greg", lastName = "Angel")
		val list = arrayListOf(p1, p2)
		val kType = typeOf<ArrayList<Person>>()
		val kTypeClass = kType.javaClass
//		converter.write(list, typeOf<ArrayList<Person>>().javaType, MediaType.APPLICATION_JSON, message)
		converter.write(list, list::class.java, MediaType.APPLICATION_JSON, message)
		println(message.bodyAsString)
		//assertThat(converter.canWrite(listOf(p).javaClass, MediaType.APPLICATION_JSON)).isTrue()
	}
}