package com.app.allride.driver.features.auth.login.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.allride.driver.MainActivity
import com.app.allride.driver.R
import com.app.allride.driver.api.ApiInterface
import com.app.allride.driver.api.Constants
import com.app.allride.driver.databinding.ActivityLoginBinding
import com.app.allride.driver.features.auth.login.model.RequestLogin
import com.app.allride.driver.features.auth.login.model.ResponseLogin
import com.app.allride.driver.utils.AppHelper
import com.app.allride.driver.utils.AppPreference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    companion object {
        private const val TAG: String = "LOGIN_ACTIVITY"
    }

    private lateinit var binding: ActivityLoginBinding

    private var email: String = ""
    private var password: String = ""
    private lateinit var mPreference: AppPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mPreference = AppPreference(this@LoginActivity)


        binding.edtEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                email = ""
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                email = binding.edtEmail.text.trim().toString()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        binding.edtPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                password = ""
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                password = binding.edtPassword.text.trim().toString()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        binding.btnLogin.setOnClickListener {
            if (email != "" && password != "") {
                val requestLogin = RequestLogin(
                    "1.0.3", "android", "",
                    email, password
                )
                doLogin(requestLogin)
            } else {
                AppHelper.toastShort(this@LoginActivity, "Fields shouldn't be empty!!!")
            }

        }
    }

    private fun doLogin(requestLogin: RequestLogin) {
        if (AppHelper.isConnectedToInternet(this@LoginActivity)) {

            // Show Loading dialog
            AppHelper.showLoading(this@LoginActivity)
            val call: Call<ResponseLogin> = ApiInterface.create().login(Constants.API_CONTENT_TYPE, Constants.API_CONTENT_TYPE, Constants.DEVICE_TYPE, requestLogin)
            call.enqueue(object : Callback<ResponseLogin> {
                override fun onResponse(
                    call: Call<ResponseLogin>,
                    response: Response<ResponseLogin>
                ) {
                    // Hide Loading dialog
                    AppHelper.hideLoading()
                    if (response.isSuccessful) {
                        AppHelper.showDebugLog(TAG, "${response.body()}")
                        val mData = response.body()!!
                        if (mData.success) {
                            if (mData.data.userType == "driver") {

                                // Save data in shared preference / local storage
                                mPreference.setIsLoggedIn(true)
                                mPreference.setUserId(mData.data.userId)
                                mPreference.setTenantId(mData.data.tenantId)
                                mPreference.setAuth(mData.data.token)

                                // navigate to Main Activity after successful Login
                                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                                finish()

                            } else {
                                AppHelper.toastShort(this@LoginActivity, "Unauthorised Access")
                            }

                        } else {
                            AppHelper.toastShort(this@LoginActivity, mData.message)
                        }

                    } else {
                        AppHelper.toastLong(this@LoginActivity, "Something went wrong!!!")
                    }
                }

                override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                    AppHelper.hideLoading()
                    AppHelper.toastLong(this@LoginActivity, "${t.message}")
                    AppHelper.showDebugLog(TAG, "Failure Response : ${t.message}")
                }
            })

        } else {
            AppHelper.toastNetworkError(this@LoginActivity)
        }
    }
}

