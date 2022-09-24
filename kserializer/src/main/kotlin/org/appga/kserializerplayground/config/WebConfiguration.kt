package org.appga.kserializerplayground.config

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.KotlinSerializationJsonHttpMessageConverter
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebMvc
class WebConfiguration : WebMvcConfigurer {

    // README: we need explicitly add kotlin serialization converter
    @ExperimentalSerializationApi
    override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        converters.add(KotlinSerializationJsonHttpMessageConverter(Json {
//            useArrayPolymorphism = true
//            serializersModule = SerializersModule {
//                serializersModuleOf(ListSerializer(Person.serializer()))
//                serializersModuleOf(ArraySerializer(Person.serializer()))
//            }
        }))
    }

}
