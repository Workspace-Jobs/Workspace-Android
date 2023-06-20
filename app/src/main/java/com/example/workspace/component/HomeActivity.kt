package com.example.workspace.component

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workspace.R
import com.example.workspace.api.ApiService
import com.example.workspace.rogin.loginActivity
import com.example.workspace.select.AnnouncementActivity
import com.example.workspace.user.bookmarkActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeActivity : AppCompatActivity() {

    lateinit var profileAdapter: ProfileAdapter
    lateinit var headerAdapter: ProfileAdapter
    lateinit var maincolumn: RecyclerView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        maincolumn = findViewById(R.id.maincolumn)
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

        val retrofit = Retrofit.Builder()
            .baseUrl("http://13.125.207.76:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        val call = apiService.getProfiles()

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
                        add(Profile(img = R.drawable.company, name = "안드로이드 개발자", city = "서울특별시", area = "강남구"))
                        add(Profile(img = R.drawable.company, name = "프론트개발자", city = "서울특별시", area = "강남구"))
                        add(Profile(img = R.drawable.company, name = "서버 개발자", city = "광주광역시", area = "북구"))
                        add(Profile(img = R.drawable.company, name = "안드로이드 개발자", city = "서울특별시", area = "마포구"))
                        add(Profile(img = R.drawable.company, name = "보안 전문가", city = "인천광역시", area = "마계"))
                    }
                    profileAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<Profile>>, t: Throwable) {
                profileAdapter.datas.apply {
                    add(Profile(img = R.drawable.company, name = "안드로이드 개발자", city = "서울특별시", area = "강남구"))
                    add(Profile(img = R.drawable.company, name = "프론트개발자", city = "서울특별시", area = "강남구"))
                    add(Profile(img = R.drawable.company, name = "서버 개발자", city = "광주광역시", area = "북구"))
                    add(Profile(img = R.drawable.company, name = "안드로이드 개발자", city = "서울특별시", area = "마포구"))
                    add(Profile(img = R.drawable.company, name = "보안 전문가", city = "인천광역시", area = "마계"))
                }
                profileAdapter.notifyDataSetChanged()
            }
        })

        val headerList = findViewById<RecyclerView>(R.id.header_list)
        headerAdapter = ProfileAdapter(this)
        headerList.adapter = headerAdapter
        headerList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val headerData = mutableListOf<Profile>()
        headerData.add(Profile(img = R.drawable.company, name = "header1", city = "도시1", area = "지역1"))
        headerData.add(Profile(img = R.drawable.company, name = "header2", city = "도시2", area = "지역2"))
        headerData.add(Profile(img = R.drawable.company, name = "header2", city = "도시2", area = "지역2"))

        headerAdapter.datas = headerData
        headerAdapter.notifyDataSetChanged()
    }

}