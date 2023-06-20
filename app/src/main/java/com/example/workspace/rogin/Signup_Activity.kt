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
import com.example.workspace.R
import com.example.workspace.api.SignupRequest
import com.example.workspace.api.ApiService
import com.example.workspace.api.SignupResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Signup_Activity : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextConfirmPassword: EditText

    private lateinit var retrofit: Retrofit
    private lateinit var apiService: ApiService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        editTextEmail = findViewById(R.id.join_email)
        editTextPassword = findViewById(R.id.join_password)
        editTextConfirmPassword = findViewById(R.id.reconfirm)

        // Retrofit 초기화
        retrofit = Retrofit.Builder()
            .baseUrl("http://13.125.207.76:8000/")  // 서버의 주소로 변경해야 합니다.
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)

        val buttonSignup: Button = findViewById(R.id.join_button)
        buttonSignup.setOnClickListener {
            signup()
        }
        val textViewLogin: TextView = findViewById(R.id.login_text)
        textViewLogin.setOnClickListener {
            toLoginActivity()
        }

    }

    private fun signup() {
        val email = editTextEmail.text.toString().trim()
        val password = editTextPassword.text.toString().trim()
        val confirmPassword = editTextConfirmPassword.text.toString().trim()



        // 비밀번호 확인
        if (password != confirmPassword) {
            Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            return
        }
        val request = SignupRequest(email, password, confirmPassword)
        // 회원가입 요청 보내기


        apiService.signup(SignupRequest(email, password, confirmPassword)).enqueue(object : Callback<SignupResponse> {
            override fun onResponse(call: Call<SignupResponse>, response: Response<SignupResponse>) {
                if (response.isSuccessful) {
                    val signupResponse = response.body()
                    // 회원가입 성공 처리
                    if (signupResponse != null) {
                        // 회원가입이 성공적으로 완료되었을 때의 처리를 작성합니다.
                        Toast.makeText(this@Signup_Activity, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                        toLoginActivity()
                    }
                } else {
                    Log.d("signup", response.message())
                    Log.d("signup", response.code().toString())
                    // 회원가입 실패 처리
                    Toast.makeText(this@Signup_Activity, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                // 네트워크 오류 등 예외 발생 시 처리
                Toast.makeText(this@Signup_Activity, "오류가 발생했습니다.", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun toLoginActivity(){
        val intent = Intent(this, loginActivity::class.java)
        startActivity(intent)
        finish()
    }
}


