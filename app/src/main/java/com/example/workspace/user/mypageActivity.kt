package com.example.workspace.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.workspace.MainActivity
import com.example.workspace.R
import com.example.workspace.component.CommunityActivity
import com.example.workspace.component.HomeActivity
import com.example.workspace.component.commentActivity
import com.example.workspace.select.AnnouncementActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class mypageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.page_bottom)
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
                R.id.my_activity -> {
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
    }
}