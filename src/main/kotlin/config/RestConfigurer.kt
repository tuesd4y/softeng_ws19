package me.tuesd4y.backend.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter
import java.util.stream.Collectors
import javax.persistence.EntityManager
import javax.persistence.metamodel.EntityType


@Configuration
class RestConfigurer : RepositoryRestConfigurerAdapter() {

    @Autowired
    private val entityManager: EntityManager? = null

    override fun configureRepositoryRestConfiguration(config: RepositoryRestConfiguration) {
        config.exposeIdsFor(
            *entityManager!!.metamodel.entities.stream().map { e: EntityType<*> -> e.javaType }.collect(
                Collectors.toList()
            ).toTypedArray()
        )
    }
}