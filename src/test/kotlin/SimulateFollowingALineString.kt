import com.fasterxml.jackson.databind.ObjectMapper
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.json.responseJson
import java.time.LocalDateTime

object SimulateFollowingALineString {
    @JvmStatic
    fun main(args: Array<String>) {
        FuelManager.instance.basePath = "https://softeng-ws19.herokuapp.com/api/"
//        FuelManager.instance.basePath = "http://localhost:8080/api/"
        FuelManager.instance.baseHeaders = mapOf("Content-Type" to "application/json")

        // create new user
        println(
            "users".httpPost()
                .jsonBody(
                    """
                        {
                            "name": "Markus",
                            "emailAddress": "baum@mail.com"
                        }
                    """.trimIndent()
                ).responseJson()
        )

        // create first vehicle
        val vehicleLink = "vehicles".httpPost()
            .jsonBody(
                """
                {
                    "type": "ECar1"
                }
            """.trimIndent()
            ).responseJson().third.component1()!!
            .obj().getJSONObject("_links").getJSONObject("self").getString("href")

        followLineString(vehicleLink)
    }

    private fun followLineString(vehicleLink: String) {
        val points = ObjectMapper().readTree(javaClass.getResource("/testpath.json"))
            .map { it["x"].asDouble() to it["y"].asDouble() }

        // in seconds
        val timeOut = 3L

        points.forEach { point ->
            Thread.sleep(timeOut * 1000)
            println(addPosition(point.first, point.second, LocalDateTime.now(), vehicleLink))
        }
    }

    fun addPosition(x: Double, y: Double, dateTime: LocalDateTime, vehicleLink: String) = "positions".httpPost().jsonBody(
        """
            {
                "location": {
                    "latitude": $y,
                    "longitude": $x
                },
                "dateTime": "$dateTime",
                "vehicle": "$vehicleLink"
            }
        """.trimIndent()
    ).responseJson()
}
