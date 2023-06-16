package com.example.workspace.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.OneShotPreDrawListener.add
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workspace.MainActivity
import com.example.workspace.R
import com.example.workspace.component.CommunityActivity
import com.example.workspace.component.HomeActivity
import com.example.workspace.component.Profile
import com.example.workspace.component.ProfileAdapter
import com.example.workspace.component.commentActivity
import com.example.workspace.select.AnnouncementActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class bookmarkActivity : AppCompatActivity() {

    lateinit var profileAdapter: ProfileAdapter

    lateinit var listbook: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmark)

        listbook = findViewById(R.id.list_book)
        initRecycler()


        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bookmark_bottom)
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

    private fun initRecycler() {
        profileAdapter = ProfileAdapter(this)

        listbook.adapter = profileAdapter

        val gridLayoutManager = GridLayoutManager(this, 2) // 열의 개수를 2로 지정하고자 한다면, 숫자를 변경해주시면 됩니다.
        listbook.layoutManager = gridLayoutManager

        profileAdapter.datas.apply {
            add(Profile(img = R.drawable.ic_launcher_background, name = "mary", city = "서울특별시", area = "강남구"))
            add(Profile(img = R.drawable.ic_launcher_background, name = "jenny", city = "서울특별시", area = "강남구"))
            add(Profile(img = R.drawable.ic_launcher_background, name = "jhon", city = "광주광역시", area = "북구"))
            add(Profile(img = R.drawable.ic_launcher_background, name = "ruby", city = "서울특별시", area = "마포구"))
            add(Profile(img = R.drawable.ic_launcher_background, name = "yuna", city = "인천광역시", area = "마계"))
        }

        profileAdapter.notifyDataSetChanged()
    }
}