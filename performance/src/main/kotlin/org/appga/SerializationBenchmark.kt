package org.appga.kserializerplayground.performance

import kotlinx.serialization.json.Json
import org.appga.kserializerplayground.model.Person
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
private val personSerializer = Person.serializer()
private val person1 = Person(id = 123, email = "mblack@domain.com", firstName = "Mike", lastName = "Black")
private val person1str = """
{"id": 123, "email": "mblack@domain.com", "firstName": "Mike", "lastName": "Black"}	
""".trimIndent()

@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 10, time = 1)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
@Fork(2)
open class SerializationBenchmark {

	@Benchmark
	open fun serializePerson() {
		json.encodeToString(personSerializer, person1)
	}

	@Benchmark
	open fun deserializePerson() {
		json.decodeFromString(personSerializer, person1str)
	}
}