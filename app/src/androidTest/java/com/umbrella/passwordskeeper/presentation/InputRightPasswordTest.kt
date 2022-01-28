package com.umbrella.passwordskeeper.presentation


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.umbrella.passwordskeeper.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class InputRightPasswordTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun inputRightPasswordTest() {
        val textInputEditText = onView(
            allOf(withId(R.id.password_input_field),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("com.google.android.material.textfield.TextInputLayout")),
                        0),
                    0),
                isDisplayed()))
        textInputEditText.perform(replaceText("Password14"), closeSoftKeyboard())

        val materialButton = onView(
            allOf(withId(R.id.button_enter), withText("Войти"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.main_container),
                        0),
                    2),
                isDisplayed()))
        materialButton.perform(click())

        val imageButton = onView(
            allOf(withId(R.id.add_new_password),
                withParent(withParent(withId(R.id.main_container))),
                isDisplayed()))
        imageButton.check(matches(isDisplayed()))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int,
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
