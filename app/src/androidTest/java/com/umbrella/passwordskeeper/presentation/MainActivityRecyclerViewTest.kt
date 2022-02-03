package com.umbrella.passwordskeeper.presentation

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.umbrella.passwordskeeper.R
import com.umbrella.passwordskeeper.presentation.adapter.PasswordItemViewHolder
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityRecyclerViewTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @After
    fun close() {
        scenario.close()
    }

    @Test
    fun checkNavigationAfterEnterPassword() {
        onView(withId(R.id.password_input_field)).perform(typeText("Password14"))
        onView(withId(R.id.button_enter)).perform(click())
        onView(withId(R.id.add_new_password)).check(matches(isDisplayed()))
    }

    @Test
    fun checkNavigationAfterPressingAddNewPasswordButton() {
        onView(withId(R.id.password_input_field)).perform(typeText("Password14"))
        onView(withId(R.id.button_enter)).perform(click())
        onView(withId(R.id.add_new_password)).perform(click())
        onView(withId(R.id.create_password_conditions)).check(matches(isDisplayed()))
    }

    @Test
    fun checkInsertNewItemInRecyclerView() {
        onView(withId(R.id.password_input_field)).perform(typeText("Password14"))
        onView(withId(R.id.button_enter)).perform(click())
        onView(withId(R.id.add_new_password)).perform(click())
        onView(withId(R.id.site_input_field)).perform(typeText("vkontakte.ru"))
        onView(withId(R.id.password_input_field)).perform(typeText("Password01"))
        closeSoftKeyboard()
        onView(withId(R.id.button_save_password)).perform(click())
        onView(withId(R.id.passwords_recycler_view))
            .perform(
                RecyclerViewActions.scrollTo<PasswordItemViewHolder>(
                    hasDescendant(withText("Сайт: vkontakte.ru"))
                )
            )
    }
}