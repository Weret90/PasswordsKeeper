package com.umbrella.passwordskeeper.data.repositories

import com.umbrella.passwordskeeper.TEST_PASSWORD
import com.umbrella.passwordskeeper.data.local.LocalStorage
import com.umbrella.passwordskeeper.data.mapper.toDataModel
import com.umbrella.passwordskeeper.domain.entities.Password
import com.umbrella.passwordskeeper.domain.repositories.PasswordsRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class PasswordsRepositoryTest {

    private lateinit var passwordsRepository: PasswordsRepository
    private lateinit var localStorage: LocalStorage

    @Before
    fun setUp() {
        localStorage = mock()
        passwordsRepository = PasswordsRepositoryImpl(localStorage)
    }

    @Test
    fun `WHEN call getPasswordsList from repository EXPECT call getPasswordsList from localStorage`() {
        passwordsRepository.getPasswordsList()
        verify(localStorage, times(1)).getPasswordsList()
    }

    @Test
    fun `WHEN call addPassword from repository EXPECT call addPassword from localStorage`() {
        val correctPassword = Password("vk.com", TEST_PASSWORD)
        runBlocking {
            passwordsRepository.addPassword(correctPassword)
            verify(localStorage, times(1)).addPassword(correctPassword.toDataModel())
        }
    }

    @Test
    fun `WHEN call deletePassword from repository EXPECT call deletePassword from localStorage`() {
        val passwordForDelete = Password("vk.com", TEST_PASSWORD)
        runBlocking {
            passwordsRepository.deletePassword(passwordForDelete)
            verify(localStorage, times(1)).deletePassword(passwordForDelete.toDataModel())
        }
    }
}