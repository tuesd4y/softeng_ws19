package controllers

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import me.tuesd4y.backend.config.RedistributionApiClient
import me.tuesd4y.backend.controllers.ProblemController
import me.tuesd4y.backend.data.entity.DVehicle
import me.tuesd4y.backend.data.repositories.VehicleRepository
import org.junit.Test
import org.springframework.http.HttpStatus
import java.util.*

class ProblemControllerTest {
    @Test
    fun `check that problem controller calls the redistribution service when a new problem arrives`() {

        // setup
        val v = DVehicle()
        val repo: VehicleRepository = mockk()
        every {repo.findById(1)} returns Optional.of(v)

        val client: RedistributionApiClient = mockk()
        every { client.notifyClient(v, eq("")) } returns true

        val pc = ProblemController(repo, client)


        // test
        val createProblem = pc.createProblem(ProblemController.Problem("", 1))

        assertEquals(HttpStatus.OK, createProblem.statusCode)

        verify(exactly = 1) {
            repo.findById(1)
            client.notifyClient(v, eq(""))
        }
    }
}
