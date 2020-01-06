package me.tuesd4y.backend.data.repositories

import me.tuesd4y.backend.data.entity.DNormalUser
import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(path = "users", collectionResourceRel = "users")
interface UserRepository : CrudRepository<DNormalUser, Long> {
}