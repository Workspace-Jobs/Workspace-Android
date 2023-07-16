package com.example.workspace.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.workspace.R
import com.example.workspace.api.ApiService
import com.example.workspace.component.CommunityActivity
import com.example.workspace.component.HomeActivity
import com.example.workspace.rogin.loginActivity
import com.example.workspace.select.AnnouncementActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class mypageActivity : AppCompatActivity() {

    private lateinit var retrofit: Retrofit
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)

        val username = intent.getStringExtra("email")

        retrofit = Retrofit.Builder()
            .baseUrl("http://13.124.44.106:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        // 사용자 이름을 표시하는 TextView에 값 설정
        val usernameTextView = findViewById<TextView>(R.id.username)
        usernameTextView.text = username


        val logoutTextView: TextView = findViewById(R.id.logout)
        logoutTextView.setOnClickListener {
            logout()
        }

        val myBookmarkImageView = findViewById<ImageView>(R.id.my_bookmark)
        myBookmarkImageView.setOnClickListener {
            // 다른 액티비티로 이동하는 인텐트 생성
            val intent = Intent(this, LikeActivity::class.java)
            // 인텐트를 사용하여 액티비티 이동
            startActivity(intent)
        }
        val op = findViewById<ImageView>(R.id.offer_photo)
        op.setOnClickListener {
            val intent = Intent(this, bookmarkActivity::class.java)
            startActivity(intent)
        }
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.offer -> {
                    val intent = Intent(this, AnnouncementActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.chatting -> {
                    val intent = Intent(this, CommunityActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.youser -> {
                    val intent = Intent(this, mypageActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.bookmark -> {
                    val intent = Intent(this, bookmarkActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
        bottomNavigationView.menu.findItem(R.id.youser)?.isChecked = true
    }
    private fun logout() {
        // 로그아웃 로직 실행
        // ...

        // Toast 메시지 표시
        Toast.makeText(this, "로그아웃되었습니다.", Toast.LENGTH_SHORT).show()

        // 다른 액티비티로 이동
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish() // 현재 액티비티를 종료하여 뒤로 가기 시 돌아오지 않도록 합니다.
    }
}