package org.appga.kserializerplayground.performance

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import org.appga.kserializerplayground.model.Person
import org.junit.jupiter.api.Test
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.BenchmarkMode
import org.openjdk.jmh.annotations.Fork
import org.openjdk.jmh.annotations.Measurement
import org.openjdk.jmh.annotations.Mode
import org.openjdk.jmh.annotations.OutputTimeUnit
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State
import org.openjdk.jmh.annotations.Warmup
import java.util.concurrent.TimeUnit

private val json = Json
private val objectMapper = jacksonObjectMapper()
private val personSerializer = Person.serializer()
private val listSerializer = ListSerializer(personSerializer)
private val personlistType = object : TypeReference<List<Person>>() {}

private val person1 = Person(id = 123, email = "mblack@domain.com", firstName = "Mike", lastName = "Black")
private val person2 = Person(id = 234, email = "kthorn@domain.com", firstName = "Kyte", lastName = "Thorn")
private val person3 = Person(id = 345, email = "wsmith@domain.com", firstName = "Will", lastName = "Smith")
private val person4 = Person(id = 456, email = "anna.wylson@domain.com", firstName = "Anna", lastName = "Wylson")
private val person5 = Person(id = 56789, email = "pati@domain.com", firstName = "Patricia", lastName = "Elephant")
private val personList = listOf(person1, person2, person3, person4, person5)

private val personsJson = """
[{"id": 123, "email": "mblack@domain.com", "firstName": "Mike", "lastName": "Black"},
{"id": 234, "email": "kthorn@domain.com", "firstName": "Kyte", "lastName": "Thorn"},
{"id": 345, "email": "wsmith@domain.com", "firstName": "Will", "lastName": "Smith"},
{"id": 456, "email": "anna.wylson@domain.com", "firstName": "Anna", "lastName": "Wylson"},
{"id": 56789, "email": "pati@domain.com", "firstName": "Patricia", "lastName": "Elephant"}]	
""".trimIndent()

@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 1)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
@Fork(2)
open class PersonCollectionsBenchmark {

	@Benchmark
	@Test
	open fun kotlinSerializeList() {
		json.encodeToString(listSerializer, personList)
	}

	@Benchmark
	@Test
	open fun kotlinDeserializeList() {
		json.decodeFromString(listSerializer, personsJson)
	}

	@Benchmark
	@Test
	open fun jacksonSerializeList() {
		objectMapper.writeValueAsString(personList)
	}

	@Benchmark
	@Test
	open fun jacksonDeserializeList() {
		objectMapper.readValue(personsJson, personlistType)
	}

}