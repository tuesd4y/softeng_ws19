package me.tuesd4y.backend.data.repositories

import me.tuesd4y.backend.data.entity.DPosition
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(path = "positions", collectionResourceRel = "positions")
interface PositionRepository : CrudRepository<DPosition, Long> {
    fun findAllByVehicleId(@Param("vehicleId") vehicleId: Long): List<DPosition>

    fun findTop1ByVehicleIdOrderByDateTimeDesc(@Param("vehicleId") vehicleId: Long): DPosition
}
