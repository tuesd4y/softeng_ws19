package me.tuesd4y.backend.config

import me.tuesd4y.backend.controllers.ProblemController
import me.tuesd4y.backend.data.entity.DVehicle

interface RedistributionApiClient {
    fun notifyClient(vehicle: DVehicle, description: String): Boolean
}
