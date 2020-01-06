package me.tuesd4y.backend.data.repositories

import me.tuesd4y.backend.data.entity.DRedistributionTask
import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(path = "redistributionTasks", collectionResourceRel = "redistributionTasks")
interface RedistributionTaskRepository : CrudRepository<DRedistributionTask, Long>
