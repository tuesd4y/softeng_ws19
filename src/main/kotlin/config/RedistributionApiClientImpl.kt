package me.tuesd4y.backend.config

import com.github.kittinunf.fuel.httpPut
import me.tuesd4y.backend.data.entity.DVehicle
import me.tuesd4y.backend.data.repositories.PositionRepository
import org.springframework.stereotype.Component

@Component
class RedistributionApiClientImpl(val positionRepository: PositionRepository) : RedistributionApiClient {
    override fun notifyClient(vehicle: DVehicle, description: String): Boolean {
        val pos = positionRepository.findTop1ByVehicleIdOrderByDateTimeDesc(vehicle.id)

        println("sending request:")
        println("https://warm-ravine-22076.herokuapp.com/newTask?vehicleId=${vehicle.id}" +
                "&latStart=${pos.latitude}&longStart=${pos.longitude}" +
                "&latEnd=${pos.latitude + .2}&longEnd=${pos.longitude + .2}")

        val res =  ("https://warm-ravine-22076.herokuapp.com/newTask?vehicleId=${vehicle.id}" +
                "&latStart=${pos.latitude}&longStart=${pos.longitude}" +
                "&latEnd=${pos.latitude + .2}&longEnd=${pos.longitude + .2}").httpPut()
            .response()
            .second

        println(res)

        return 200 == res.statusCode
    }
}
