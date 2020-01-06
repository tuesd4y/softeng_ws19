package me.tuesd4y.backend.data.repositories

import me.tuesd4y.backend.data.entity.DRoute
import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(path = "routes", collectionResourceRel = "routes")
interface RouteRepository : CrudRepository<DRoute, Long> {
}