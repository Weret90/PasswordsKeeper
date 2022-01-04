package com.umbrella.passwordskeeper.data.repositories

import com.umbrella.passwordskeeper.data.local.LocalStorage
import com.umbrella.passwordskeeper.domain.repositories.AuthRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.*

class AuthRepositoryTest {

    private lateinit var repository: AuthRepository
    private lateinit var localStorage: LocalStorage

    @Before
    fun setUp() {
        localStorage = mock()
        repository = AuthRepositoryImpl(localStorage)
        whenever(localStorage.getAuthPassword()).thenReturn("Password14")
    }

    @Test
    fun `WHEN get saved password EXPECT Password14`() {
        val actual = repository.getAuthPassword()
        val expected = "Password14"
        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN get saved password EXPECT not null`() {
        assertNotNull(repository.getAuthPassword())
    }

    @Test
    fun `WHEN get password before create password EXPECT null`() {
        whenever(localStorage.getAuthPassword()).thenReturn(null)
        assertNull(repository.getAuthPassword())
    }

    @Test
    fun `WHEN input wrong password EXPECT false (wrong password)`() {
        assertFalse(repository.checkAuthPassword("Password12"))
    }

    @Test
    fun `WHEN input right password EXPECT true (right password)`() {
        assertTrue(repository.checkAuthPassword("Password14"))
    }
}