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

    @Query("select * from (select (select p from dposition p where p.vehicle_id = v.id order by p.date_time desc limit 1).*" +
            " from dvehicle v) x where x is not null",
        nativeQuery = true)
    fun findCurrentOfAllVehicles(): List<DPosition>

    @Query("select * from (select (select p from dposition p where p.vehicle_id = v.id order by p.date_time desc limit 1).*" +
            "from dvehicle v) p where st_dwithin(st_setsrid(ST_POINT(:x, :y), 4326), p.location_point, :distance * 1000, true)",
        nativeQuery = true)
    fun findNearbyVehicles(@Param("x") x: Double, @Param("y") y: Double, @Param("distance") d: Int): List<DPosition>
}
