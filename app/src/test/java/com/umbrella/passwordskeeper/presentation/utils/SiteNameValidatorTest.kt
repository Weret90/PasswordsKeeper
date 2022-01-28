package com.umbrella.passwordskeeper.presentation.utils

import com.umbrella.passwordskeeper.TEST_CORRECT_SITE_NAME
import org.junit.Assert
import org.junit.Test

class SiteNameValidatorTest {

    @Test
    fun `WHEN create empty siteName EXPECT false (incorrect siteName)`() {
        val isValid = SiteNameValidator.isValidSiteName(" ")
        Assert.assertFalse(isValid)
    }

    @Test
    fun `WHEN create null siteName EXPECT false (incorrect siteName)`() {
        val isValid = SiteNameValidator.isValidSiteName(null)
        Assert.assertFalse(isValid)
    }

    @Test
    fun `WHEN create siteName with 1 symbol EXPECT false (incorrect siteName)`() {
        val isValid = SiteNameValidator.isValidSiteName("a.com")
        Assert.assertFalse(isValid)
    }

    @Test
    fun `WHEN create siteName contains russian letters EXPECT false (incorrect siteName)`() {
        val isValid = SiteNameValidator.isValidSiteName("вконтакте.com")
        Assert.assertFalse(isValid)
    }

    @Test
    fun `WHEN create siteName without com or ru EXPECT false (incorrect siteName)`() {
        var isValid = SiteNameValidator.isValidSiteName("vk.us")
        Assert.assertFalse(isValid)
        isValid = SiteNameValidator.isValidSiteName("facebook")
        Assert.assertFalse(isValid)
    }

    @Test
    fun `WHEN create siteName contains spaces EXPECT false (incorrect siteName)`() {
        val isValid = SiteNameValidator.isValidSiteName("vk .com")
        Assert.assertFalse(isValid)
    }

    @Test
    fun `WHEN create correct siteName EXPECT true (correct siteName)`() {
        var isValid = SiteNameValidator.isValidSiteName("vk.com")
        Assert.assertTrue(isValid)
        isValid = SiteNameValidator.isValidSiteName(TEST_CORRECT_SITE_NAME)
        Assert.assertTrue(isValid)
    }
}