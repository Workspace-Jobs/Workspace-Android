package com.example.workspace.user

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workspace.R
import com.example.workspace.api.ApiService
import com.example.workspace.component.CommunityActivity
import com.example.workspace.component.HomeActivity
import com.example.workspace.api.Profile
import com.example.workspace.component.ProfileAdapter
import com.example.workspace.rogin.loginActivity
import com.example.workspace.select.AnnouncementActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
                R.id.youser -> {
                    if (isLoggedIn()) {
                        val intent = Intent(this, mypageActivity::class.java)
                        startActivity(intent)
                        true
                    } else {
                        val intent = Intent(this, loginActivity::class.java)
                        startActivity(intent)
                        true
                    }
                }
                R.id.bookmark -> {
                    val intent = Intent(this, bookmarkActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
        bottomNavigationView.menu.findItem(R.id.bookmark)?.isChecked = true

        if (!isLoggedIn()) {
            Toast.makeText(this, "북마크를 불러오는데 실패했습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initRecycler() {
        profileAdapter = ProfileAdapter(this)

        listbook.adapter = profileAdapter

        val gridLayoutManager = GridLayoutManager(this, 2) // 열의 개수를 2로 지정하고자 한다면, 숫자를 변경해주시면 됩니다.
        listbook.layoutManager = gridLayoutManager

        val retrofit = Retrofit.Builder()
            .baseUrl("http://13.124.44.106:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

//        val call = apiService.getProfiles(1)
//        call.enqueue(object : Callback<List<Profile>> {
//            override fun onResponse(call: Call<List<Profile>>, response: Response<List<Profile>>) {
//                if (response.isSuccessful) {
//                    val profiles = response.body()
//                    if (profiles != null) {
//                        profileAdapter.datas.addAll(profiles)
//                        profileAdapter.notifyDataSetChanged()
//                    }
//                } else {
//                    Log.d("mark",response.message())
//                    Toast.makeText(this@bookmarkActivity, "북마크를 불러오는데 실패했습니다.", Toast.LENGTH_SHORT)
//                        .show()
//                    profileAdapter.datas.apply {
//                       }
//                    profileAdapter.notifyDataSetChanged()
//                }
//            }
//
//            override fun onFailure(call: Call<List<Profile>>, t: Throwable) {
//                profileAdapter.datas.apply {
//                    }
//                profileAdapter.notifyDataSetChanged()
//            }
//        })


    }

    fun onBackButtonClicked(view: View) {
        onBackPressed()
    }
    private fun isLoggedIn(): Boolean {

        // 예시: 로그인 여부를 SharedPreferences에서 확인하는 경우
        val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
        return isLoggedIn
    }

}