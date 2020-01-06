package me.tuesd4y.backend.data.repositories

import me.tuesd4y.backend.data.entity.DRental
import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(path = "rentals", collectionResourceRel = "rentals")
interface RentalRepository : CrudRepository<DRental, Long> {
}