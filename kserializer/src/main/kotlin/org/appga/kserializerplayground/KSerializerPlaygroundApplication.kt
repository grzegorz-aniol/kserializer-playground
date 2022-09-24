package org.appga.kserializerplayground

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KSerializerPlaygroundApplication

fun main(args: Array<String>) {
	runApplication<KSerializerPlaygroundApplication>(*args)
}
