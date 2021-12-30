package com.umbrella.passwordskeeper.presentation.utils

import java.util.regex.Pattern

class PasswordValidator {

    companion object {
        private const val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])$"

        fun isValidCreatedPassword(password: String?): Boolean {
            return (password != null
                    && password.isNotBlank()
                    && password.length >= 8
                    && Pattern.compile(PASSWORD_PATTERN).matcher(password).matches())
        }

        fun isValidInputPassword(password: String?): Boolean {
            return (password != null && password.isNotBlank())
        }
    }
}