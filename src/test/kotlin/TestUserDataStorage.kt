import junit.framework.Assert.*
import org.junit.Assert
import org.junit.Test
import java.lang.NullPointerException

class TestUserDataStorage {
    val testData = arrayOf(
        arrayOf("Chris", 1.toString(), "pass"),
        arrayOf("Sebastian", 2.toString(), "passinger"),
        arrayOf("Daniel", 3.toString(), "passwörtchen"),
        arrayOf("Richard", 4.toString(), "passwörtlein"),
        arrayOf("Felix", 5.toString(), "testpasswort")
    )

    @Test
    fun `check if finding users by password and username works correctly`() {
        // setup
        val ds = DataStorage()
        testData.forEach {
            ds.store(it[0], it[1], it[2])
        }

        // test existing users return true
        testData.forEach {
            Assert.assertTrue("User should be found", ds.findUser(it[0], it[1], it[2]))
        }

        // test unknown user
        try {
            ds.findUser("", "", "")
            assertTrue("expected Exception should be thrown", false)
        } catch (ignored: NullPointerException) {
        }

        // test username and password returns correct user
        testData.forEach {
            assertEquals("findUserByUsernameAndPassword should return correct user object",
                ds.findUser(it[0], it[2]), User(it[0], it[1], it[2]))
        }
    }
}
