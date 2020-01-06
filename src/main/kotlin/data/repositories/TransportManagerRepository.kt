package me.tuesd4y.backend.data.repositories

import me.tuesd4y.backend.data.entity.DTransportManager
import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(path = "transportManagers", collectionResourceRel = "transportManagers")
interface TransportManagerRepository : CrudRepository<DTransportManager, Long> {
}