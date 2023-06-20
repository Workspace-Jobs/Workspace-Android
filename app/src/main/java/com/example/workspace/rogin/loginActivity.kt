package com.example.workspace.rogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.workspace.api.LoginRequest
import com.example.workspace.R
import com.example.workspace.api.ApiService
import com.example.workspace.api.LoginResponse
import com.example.workspace.user.mypageActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class loginActivity : AppCompatActivity() {
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var retrofit: Retrofit
    private lateinit var apiService: ApiService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        editTextEmail = findViewById(R.id.login_email)
        editTextPassword = findViewById(R.id.login_password)

        retrofit = Retrofit.Builder()
            .baseUrl("http://13.125.207.76:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)

        val buttonSignup: Button = findViewById(R.id.login_button)
        buttonSignup.setOnClickListener {
            login()
            finish()
        }
        val textViewSignUp: TextView = findViewById(R.id.textView_sign_up)
        textViewSignUp.setOnClickListener {
            val intent = Intent(this, Signup_Activity::class.java)
            startActivity(intent)
        }

    }

    private fun login() {
        val email = editTextEmail.text.toString().trim()
        val password = editTextPassword.text.toString().trim()

        // 로그인 요청 보내기
        apiService.login(LoginRequest(email, password)).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {
                        val loginResponse = response.body()

                        if (loginResponse != null) {

                            Toast.makeText(this@loginActivity, "로그인이 완료되었습니다.", Toast.LENGTH_SHORT)
                                .show()
                            val intent = Intent(this@loginActivity, mypageActivity::class.java)
                            intent.putExtra("email", email)
                            startActivity(intent)
                            finish()
                        }
                    } else {
                        Log.d("signup", response.message())
                        Log.d("signup", response.code().toString())
                        Toast.makeText(this@loginActivity, "로그인에 실패했습니다.", Toast.LENGTH_SHORT)
                            .show()
                    }

            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("error", t.message.toString())
                // 네트워크 오류 등 예외 발생 시 처리
                Toast.makeText(this@loginActivity, "오류가 발생했습니다.", Toast.LENGTH_SHORT).show()
            }
        })
    }
}