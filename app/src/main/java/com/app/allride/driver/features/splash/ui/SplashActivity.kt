package com.app.allride.driver.features.splash.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.app.allride.driver.MainActivity
import com.app.allride.driver.R
import com.app.allride.driver.databinding.ActivitySplashBinding
import com.app.allride.driver.features.auth.login.ui.LoginActivity
import com.app.allride.driver.utils.AppPreference

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    companion object {
        private const val TAG: String = "SPLASH_ACTIVITY"
    }

    private lateinit var binding: ActivitySplashBinding
    private lateinit var mPreference: AppPreference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mPreference = AppPreference(this@SplashActivity)

        val fadeIn = android.view.animation.AnimationUtils.loadAnimation(this, R.anim.fade_in)
        val fadeOut = android.view.animation.AnimationUtils.loadAnimation(this, R.anim.fade_out)

        binding.imgSplashLogo.startAnimation(fadeIn)

        fadeIn.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
                binding.imgSplashLogo.startAnimation(fadeOut)
                if(mPreference.isLoggedIn()){
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                }else{
                    startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                    finish()
                }

            }

            override fun onAnimationRepeat(p0: Animation?) {
            }

        })
    }
}