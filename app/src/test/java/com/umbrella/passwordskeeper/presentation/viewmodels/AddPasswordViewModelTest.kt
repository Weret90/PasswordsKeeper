package com.umbrella.passwordskeeper.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.runner.AndroidJUnit4
import com.umbrella.passwordskeeper.domain.usecases.AddPasswordUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class AddPasswordViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    private lateinit var addPasswordViewModel: AddPasswordViewModel

    @Mock
    private lateinit var addPasswordUseCase: AddPasswordUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        addPasswordViewModel = AddPasswordViewModel(addPasswordUseCase)
    }

    @Test
    fun coroutines_TestReturnValueIsNotNull() {
        testCoroutineRule.runBlockingTest {
            val observer = Observer<Unit?> {}
            val liveData = addPasswordViewModel.successSavePassword

            `when`(addPasswordUseCase(any())).then {}

            try {
                liveData.observeForever(observer)
                addPasswordViewModel.addNewPassword("facebook.com", "Password14")
                Assert.assertNotNull(liveData.value)
            } finally {
                liveData.removeObserver(observer)
            }
        }
    }
}
