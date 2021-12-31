package com.umbrella.passwordskeeper.presentation.utils

import java.util.regex.Pattern

class SiteNameValidator {
    companion object {
        private const val SITE_NAME_PATTERN = "^[0-9a-zA-Z]{2,}\\.(com|ru)$"

        fun isValidSiteName(site: String?): Boolean {
            return (site != null
                    && site.isNotBlank()
                    && Pattern.compile(SITE_NAME_PATTERN).matcher(site).matches())
        }
    }
}