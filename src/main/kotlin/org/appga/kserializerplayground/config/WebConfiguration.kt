package org.appga.kserializerplayground.config

import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.KotlinSerializationJsonHttpMessageConverter
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebMvc
class WebConfiguration : WebMvcConfigurer {

    // README: we need explicitly add kotlin serialization converter
    override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        converters.add(KotlinSerializationJsonHttpMessageConverter())
    }

}
