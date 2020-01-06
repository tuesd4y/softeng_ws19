package me.tuesd4y.backend.config

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import org.geolatte.geom.G2D
import org.geolatte.geom.Geometries
import org.geolatte.geom.Point
import org.geolatte.geom.crs.CoordinateReferenceSystems
import java.io.IOException

class PointDeserializer : StdDeserializer<Point<G2D>>(Point::class.java) {
    companion object {
        const val WGS84SRID = 4326
    }

    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Point<G2D> {
        val node = p.readValueAsTree<JsonNode>()
        val latitude = node.get("lat").doubleValue()
        val longitude = node.get("long").doubleValue()
        return Geometries.mkPoint<G2D>(G2D(longitude, latitude), CoordinateReferenceSystems.WGS84)
    }
}