package me.tuesd4y.backend.data.repositories

import me.tuesd4y.backend.data.entity.DVehicle
import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(path = "vehicles", collectionResourceRel = "vehicles")
interface VehicleRepository : CrudRepository<DVehicle, Long> {
}