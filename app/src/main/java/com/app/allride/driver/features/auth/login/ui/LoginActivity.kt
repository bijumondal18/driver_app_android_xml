package com.app.allride.driver.features.auth.login.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.allride.driver.MainActivity
import com.app.allride.driver.R
import com.app.allride.driver.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    companion object {
        private const val TAG: String = "LOGIN_ACTIVITY"
    }

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.btnLogin.setOnClickListener {
//            //Todo: call login api here
//            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
//            finish()
//        }
    }
}