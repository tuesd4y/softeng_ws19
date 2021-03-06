package me.tuesd4y.backend.controllers

import me.tuesd4y.api.entities.Vehicle
import me.tuesd4y.backend.config.RedistributionApiClient
import me.tuesd4y.backend.data.entity.DVehicle
import me.tuesd4y.backend.data.repositories.VehicleRepository
import org.springframework.data.rest.webmvc.BasePathAwareController
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import java.util.*
import javax.swing.text.html.Option

@BasePathAwareController
@RequestMapping("problems")
class ProblemController(val vehicleRepository: VehicleRepository,
                        val redistributionApiClient: RedistributionApiClient) {

    data class Problem(val description: String, val vehicleId: Long)

    @PostMapping
    fun createProblem(@RequestBody problem: Problem): ResponseEntity<String> {
        val vehicle: Optional<DVehicle> = vehicleRepository.findById(problem.vehicleId)
        // vehicle not found
        if(!vehicle.isPresent()) {
            return ResponseEntity.notFound().build()
        }

        val res = redistributionApiClient.notifyClient(vehicle.get(), problem.description)

        if(!res) return ResponseEntity.badRequest().build()

        return ResponseEntity.ok("created new problem")
    }
}
