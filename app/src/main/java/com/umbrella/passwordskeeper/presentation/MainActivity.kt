package com.umbrella.passwordskeeper.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.umbrella.passwordskeeper.R
import com.umbrella.passwordskeeper.presentation.fragments.AuthFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, AuthFragment())
            .commit()
    }
}