package me.tuesd4y.backend.config

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.cfg.MapperConfig
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.introspect.AnnotatedField
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module
import com.sun.media.sound.DLSSampleLoop
import me.tuesd4y.backend.data.entity.DLocation
import org.geolatte.geom.Point
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration

@Configuration
class CustomObjectMapperConfiguration {

    @Autowired
    fun objectMapperConfig(jackson2ObjectMapper: ObjectMapper) {

        val pointModule = SimpleModule()
        pointModule.addSerializer(PointSerializer())
        pointModule.addDeserializer(Point::class.java, PointDeserializer())
//
//        val locationDeserializer = SimpleModule()
//        locationDeserializer.addDeserializer(DLocation::class.java, object : StdDeserializer<DLocation>(DLocation::class.java) {
//            override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): DLocation {
//                TODO("not implemented")
//            }
//        })

//        jackson2ObjectMapper.propertyNamingStrategy = object : PropertyNamingStrategy() {
//            override fun nameForSetterMethod(
//                config: MapperConfig<*>?,
//                method: AnnotatedMethod?,
//                defaultName: String?
//            ) = defaultName?.replace("_", "")
//
//            override fun nameForGetterMethod(
//                config: MapperConfig<*>?,
//                method: AnnotatedMethod?,
//                defaultName: String?
//            ) = defaultName?.replace("_", "")
//
//            override fun nameForField(config: MapperConfig<*>?, field: AnnotatedField?, defaultName: String?) =
//                defaultName?.replace("_", "")
//
//            override fun nameForConstructorParameter(
//                config: MapperConfig<*>?,
//                ctorParam: AnnotatedParameter?,
//                defaultName: String?
//            ) = defaultName?.let { "$it" }
//
//        }
        jackson2ObjectMapper.registerModules(pointModule)
        jackson2ObjectMapper.registerModule(Hibernate5Module())
    }
}
