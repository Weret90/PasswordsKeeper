package com.umbrella.passwordskeeper.presentation.fragments

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.umbrella.passwordskeeper.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AuthFragmentTest {

    private lateinit var scenario: FragmentScenario<AuthFragment>

    @Before
    fun setup() {
        //Запускаем Fragment в корне Activity
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_PasswordsKeeper)
    }

    @Test
    fun fragment_checkViews() {
        onView(withId(R.id.button_enter)).check(matches(withText("Войти")))
        onView(withId(R.id.password_input_field)).check(matches(withHint("Введите пароль")))
    }
}