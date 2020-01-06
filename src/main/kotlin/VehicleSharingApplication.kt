package me.tuesd4y.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
class VehicleSharingApplication

fun main(args: Array<String>) {
    runApplication<VehicleSharingApplication>(*args)
}