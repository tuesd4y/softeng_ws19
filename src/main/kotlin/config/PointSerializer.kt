package me.tuesd4y.backend.config

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import org.geolatte.geom.Point

class PointSerializer : StdSerializer<Point<*>>(Point::class.java){
    override fun serialize(point: Point<*>?, gen: JsonGenerator, provider: SerializerProvider) {
        if(point == null)
            return

        gen.writeStartObject()
        gen.writeNumberField("long", point.position.getCoordinate(0))
        gen.writeNumberField("lat", point.position.getCoordinate(1))
        gen.writeEndObject()
    }
}