package com.example.workspace.select

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workspace.R
import com.example.workspace.api.ApiService
import com.example.workspace.api.Profile
import com.example.workspace.component.ProfileAdapter
import com.google.android.material.appbar.AppBarLayout
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class offerlistActivity : AppCompatActivity() {
    lateinit var profileAdapter: ProfileAdapter

    lateinit var offerlist: RecyclerView

    private lateinit var appBarLayout: AppBarLayout

    private lateinit var toResumeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offerlist)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val comment = findViewById<TextView>(R.id.company_comment)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val bookInButton = findViewById<Button>(R.id.book_in)
        bookInButton.setOnClickListener {
            // 북마크에 추가되었다는 메시지를 표시
            Toast.makeText(this, "북마크에 추가되었습니다.", Toast.LENGTH_SHORT).show()
        }

        appBarLayout = findViewById(R.id.appBarLayout)
        offerlist = findViewById(R.id.list_offer)

        initRecycler()

        val interceptor = HttpLoggingInterceptor { message -> Log.v("HTTP", message) }
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit = Retrofit.Builder()
            .baseUrl("http://13.124.44.106:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        profileAdapter = ProfileAdapter(this)
        offerlist.adapter = profileAdapter

        val apiService = retrofit.create(ApiService::class.java)

        // API 호출
        val call1 = apiService.getProfiles(1)
        call1.enqueue(object : Callback<List<Profile>> {
            override fun onResponse(call: Call<List<Profile>>, response: Response<List<Profile>>) {
                if (response.isSuccessful) {
                    val profiles = response.body()
                    if (profiles != null) {
                        profileAdapter.datas.addAll(profiles)
                        profileAdapter.notifyDataSetChanged()
                    }
                } else {
                    // API 호출 실패 처리
                }
            }

            override fun onFailure(call: Call<List<Profile>>, t: Throwable) {
                // API 호출 실패 처리
            }
        })

        val call = apiService.getProfile(15)
        call.enqueue(object : Callback<Profile> {
            override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
                if (response.isSuccessful) {
                    Log.d("comment", "success")
                    val profiles = response.body()
                    if (profiles != null) {
                        Log.d("comment", "content : ${profiles.centent}")
                        comment.text = profiles.centent
                    }
                } else {
                    Log.d("comment", "failure")
                    // API 호출 실패 처리
                }
            }

            override fun onFailure(call: Call<Profile>, t: Throwable) {
                // API 호출 실패 처리
            }
        })

        toResumeButton = findViewById(R.id.to_resume)

        // 버튼 클릭 리스너 설정
        toResumeButton.setOnClickListener {
            // 다른 액티비티로 이동하는 인텐트 생성
            val intent = Intent(this, Resume_Activity::class.java)
            // 인텐트를 사용하여 액티비티 이동
            startActivity(intent)
        }
    }


    private fun initRecycler() {
        profileAdapter = ProfileAdapter(this)

        offerlist.adapter = profileAdapter

        val gridLayoutManager = GridLayoutManager(this, 2) // 열의 개수를 2로 지정하고자 한다면, 숫자를 변경해주시면 됩니다.
        offerlist.layoutManager = gridLayoutManager


        profileAdapter.datas.apply {

              }


    }

    private fun setupAppBarScrollBehavior() {
        offerlist.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    // 스크롤 아래로 이동하면 AppBarLayout 축소
                    appBarLayout.setExpanded(false, true)
                } else if (dy < 0) {
                    // 스크롤 위로 이동하면 AppBarLayout 확장
                    appBarLayout.setExpanded(true, true)
                }
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed() // 뒤로가기 버튼을 눌렀을 때의 동작을 처리
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}