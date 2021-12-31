package com.umbrella.passwordskeeper.presentation.utils

class SiteNameValidator {
    companion object {
        private const val MIN_LETTER_COUNT = 5
        private const val DOT = "."
        private const val SPACE = " "

        fun isValidSiteName(site: String?): Boolean {
            return (site != null
                    && site.isNotBlank())
                    && site.length >= MIN_LETTER_COUNT
                    && site.contains(DOT)
                    && !site.contains(SPACE)
        }
    }
}