package com.example.workspace.select

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workspace.R
import com.example.workspace.component.Profile
import com.example.workspace.component.ProfileAdapter
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.appbar.MaterialToolbar

class offerlistActivity : AppCompatActivity() {
    lateinit var profileAdapter: ProfileAdapter

    lateinit var offerlist: RecyclerView

    private lateinit var appBarLayout: AppBarLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offerlist)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        appBarLayout = findViewById(R.id.appBarLayout)
        offerlist = findViewById(R.id.list_offer)

        initRecycler()
    }


    private fun initRecycler() {
        profileAdapter = ProfileAdapter(this)

        offerlist.adapter = profileAdapter

        val gridLayoutManager = GridLayoutManager(this, 2) // 열의 개수를 2로 지정하고자 한다면, 숫자를 변경해주시면 됩니다.
        offerlist.layoutManager = gridLayoutManager

        profileAdapter.datas.apply {
            profileAdapter.datas.apply {
                add(Profile(img = R.drawable.company, name = "안드로이드 개발자", city = "서울특별시", area = "강남구"))
                add(Profile(img = R.drawable.company, name = "프론트개발자", city = "서울특별시", area = "강남구"))
                add(Profile(img = R.drawable.company, name = "서버 개발자", city = "광주광역시", area = "북구"))
                add(Profile(img = R.drawable.company, name = "안드로이드 개발자", city = "서울특별시", area = "마포구"))
                add(Profile(img = R.drawable.company, name = "보안 전문가", city = "인천광역시", area = "마계"))
                add(Profile(img = R.drawable.company, name = "안드로이드 개발자", city = "서울특별시", area = "강남구"))
                add(Profile(img = R.drawable.company, name = "프론트개발자", city = "서울특별시", area = "강남구"))
                add(Profile(img = R.drawable.company, name = "서버 개발자", city = "광주광역시", area = "북구"))
                add(Profile(img = R.drawable.company, name = "안드로이드 개발자", city = "서울특별시", area = "마포구"))
                add(Profile(img = R.drawable.company, name = "보안 전문가", city = "인천광역시", area = "마계"))
                add(Profile(img = R.drawable.company, name = "안드로이드 개발자", city = "서울특별시", area = "강남구"))
                add(Profile(img = R.drawable.company, name = "프론트개발자", city = "서울특별시", area = "강남구"))
                add(Profile(img = R.drawable.company, name = "서버 개발자", city = "광주광역시", area = "북구"))
                add(Profile(img = R.drawable.company, name = "안드로이드 개발자", city = "서울특별시", area = "마포구"))
                add(Profile(img = R.drawable.company, name = "보안 전문가", city = "인천광역시", area = "마계"))
            }
            profileAdapter.notifyDataSetChanged()
        }

        profileAdapter.notifyDataSetChanged()
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
}