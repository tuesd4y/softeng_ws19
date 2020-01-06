package me.tuesd4y.backend.data.repositories

import me.tuesd4y.backend.data.entity.DMaintenance
import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(path = "maintenances", collectionResourceRel = "maintenances")
interface MaintenanceRepository : CrudRepository<DMaintenance, Long> {
}