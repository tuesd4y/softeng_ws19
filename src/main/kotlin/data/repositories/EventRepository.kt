package me.tuesd4y.backend.data.repositories

import me.tuesd4y.backend.data.entity.DEvent
import me.tuesd4y.backend.data.entity.DMaintenance
import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(path = "events", collectionResourceRel = "events")
interface EventRepository : CrudRepository<DEvent, Long> {
}
