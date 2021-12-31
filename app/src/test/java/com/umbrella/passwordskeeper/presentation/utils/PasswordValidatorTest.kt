package com.umbrella.passwordskeeper.presentation.utils

import org.junit.Assert.assertFalse
import kotlin.test.Test
import kotlin.test.assertTrue

class PasswordValidatorTest {

    @Test
    fun `WHEN create empty password EXPECT false (incorrect password)`() {
        val isValid = PasswordValidator.isValidCreatedPassword("")
        assertFalse(isValid)
    }

    @Test
    fun `WHEN create null password EXPECT false (incorrect password)`() {
        val isValid = PasswordValidator.isValidCreatedPassword(null)
        assertFalse(isValid)
    }

    @Test
    fun `WHEN create password with length 5 EXPECT false (incorrect password)`() {
        val isValid = PasswordValidator.isValidCreatedPassword("Pass4")
        assertFalse(isValid)
    }

    @Test
    fun `WHEN create password with length 11 EXPECT false (incorrect password)`() {
        val isValid = PasswordValidator.isValidCreatedPassword("Password145")
        assertFalse(isValid)
    }

    @Test
    fun `WHEN create password without lower case EXPECT false (incorrect password)`() {
        val isValid = PasswordValidator.isValidCreatedPassword("PASSWORD14")
        assertFalse(isValid)
    }

    @Test
    fun `WHEN create password without upper case EXPECT false (incorrect password)`() {
        val isValid = PasswordValidator.isValidCreatedPassword("password14")
        assertFalse(isValid)
    }

    @Test
    fun `WHEN create password without numbers EXPECT false (incorrect password)`() {
        val isValid = PasswordValidator.isValidCreatedPassword("Password")
        assertFalse(isValid)
    }

    @Test
    fun `WHEN create password contains russian letters EXPECT false (incorrect password)`() {
        val isValid = PasswordValidator.isValidCreatedPassword("пароль14")
        assertFalse(isValid)
    }

    @Test
    fun `WHEN create password contains spaces EXPECT false (incorrect password)`() {
        val isValid = PasswordValidator.isValidCreatedPassword("Password 1")
        assertFalse(isValid)
    }

    @Test
    fun `WHEN create correct password EXPECT true (correct password)`() {
        val isValid = PasswordValidator.isValidCreatedPassword("Password14")
        assertTrue(isValid)
    }
}