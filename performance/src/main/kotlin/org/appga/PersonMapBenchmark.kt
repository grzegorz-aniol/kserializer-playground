package org.appga.kserializerplayground.performance

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
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
private val mapSerializer = MapSerializer(Int.serializer(), personSerializer)
private val personMapType = object : TypeReference<Map<Int, Person>>() {}

private val person1 = Person(id = 123, email = "mblack@domain.com", firstName = "Mike", lastName = "Black")
private val person2 = Person(id = 234, email = "kthorn@domain.com", firstName = "Kyte", lastName = "Thorn")
private val person3 = Person(id = 345, email = "wsmith@domain.com", firstName = "Will", lastName = "Smith")
private val person4 = Person(id = 456, email = "anna.wylson@domain.com", firstName = "Anna", lastName = "Wylson")
private val person5 = Person(id = 56789, email = "pati@domain.com", firstName = "Patricia", lastName = "Elephant")
private val personMap = mapOf(123 to person1, 234 to person2, 345 to person3, 456 to person4, 56789 to person5)

private val personsJson = """
{"123": {"id": 123, "email": "mblack@domain.com", "firstName": "Mike", "lastName": "Black"},
"234": {"id": 234, "email": "kthorn@domain.com", "firstName": "Kyte", "lastName": "Thorn"},
"345": {"id": 345, "email": "wsmith@domain.com", "firstName": "Will", "lastName": "Smith"},
"456": {"id": 456, "email": "anna.wylson@domain.com", "firstName": "Anna", "lastName": "Wylson"},
"56789": {"id": 56789, "email": "pati@domain.com", "firstName": "Patricia", "lastName": "Elephant"}}	
""".trimIndent()

@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 1)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
@Fork(2)
open class PersonMapBenchmark {

	@Benchmark
	@Test
	open fun kotlinSerializeMap() {
		json.encodeToString(mapSerializer, personMap)
	}

	@Benchmark
	@Test
	open fun kotlinDeserializeMap() {
		json.decodeFromString(mapSerializer, personsJson)
	}

	@Benchmark
	@Test
	open fun jacksonSerializeMap() {
		objectMapper.writeValueAsString(personMap)
	}

	@Benchmark
	@Test
	open fun jacksonDeserializeMap() {
		objectMapper.readValue(personsJson, personMapType)
	}

}