package com.example.workspace.component

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workspace.R
import com.example.workspace.api.ApiService
import com.example.workspace.api.Profile
import com.example.workspace.rogin.loginActivity
import com.example.workspace.select.AnnouncementActivity
import com.example.workspace.user.bookmarkActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeActivity : AppCompatActivity() {

    lateinit var profileAdapter: ProfileAdapter
    lateinit var headerAdapter: ProfileAdapter
    lateinit var maincolumn: RecyclerView
    lateinit var headerList: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        maincolumn = findViewById(R.id.maincolumn)
        headerList = findViewById(R.id.header_list)
        initRecycler()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.home_bottom)
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
                    val intent = Intent(this, loginActivity::class.java)
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
        bottomNavigationView.menu.findItem(R.id.home)?.isChecked = true
    }
    private fun initRecycler() {
        profileAdapter  = ProfileAdapter(this)
        maincolumn.adapter = profileAdapter

        val gridLayoutManager = GridLayoutManager(this, 2) // 열의 개수를 2로 지정하고자 한다면, 숫자를 변경해주시면 됩니다.
        maincolumn.layoutManager = gridLayoutManager

        val headerLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        headerList.layoutManager = headerLayoutManager

        val interceptor = HttpLoggingInterceptor { message -> Log.v("HTTP", message) }
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit = Retrofit.Builder()
            .baseUrl("http://13.124.44.106:8000/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)
        val call = apiService.getProfiles(1)

        call.enqueue(object : Callback<List<Profile>> {
            override fun onResponse(call: Call<List<Profile>>, response: Response<List<Profile>>) {
                if (response.isSuccessful) {
                    val profiles = response.body()
                    if (profiles != null) {
                        profileAdapter.datas.addAll(profiles)
                        profileAdapter.notifyDataSetChanged()
                    }
                } else {
                    profileAdapter.datas.apply {
                    }
                }
            }

            override fun onFailure(call: Call<List<Profile>>, t: Throwable) {
                profileAdapter.datas.apply {
                    }
            }
        })

        val headerData = mutableListOf<Profile>()
        headerAdapter = ProfileAdapter(this)
        headerAdapter.datas = headerData
        headerList.adapter = headerAdapter

        val headerCall = apiService.getProfiles(1)

        headerCall.enqueue(object : Callback<List<Profile>> {
            override fun onResponse(call: Call<List<Profile>>, response: Response<List<Profile>>) {
                if (response.isSuccessful) {
                    val profiles = response.body()
                    if (profiles != null) {
                        headerData.addAll(profiles)
                        headerAdapter.notifyDataSetChanged()
                    } else {
                    }
                } else {
                    // Handle error
                }
            }

            override fun onFailure(call: Call<List<Profile>>, t: Throwable) {
                // Handle failure
            }
        })
    }

}