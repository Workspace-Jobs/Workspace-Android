package com.example.workspace.component

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workspace.R
import com.example.workspace.rogin.loginActivity
import com.example.workspace.select.AnnouncementActivity
import com.example.workspace.user.bookmarkActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import de.hdodenhof.circleimageview.CircleImageView

class CommunityActivity : AppCompatActivity() {

    private lateinit var profileAdapter: UserlistAdapter
    private lateinit var cum: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community)

        cum = findViewById(R.id.listCommunity)
        inRecycler()


        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.community_bottom)
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
        bottomNavigationView.menu.findItem(R.id.chatting)?.isChecked = true

        val wlrvlfButton = findViewById<CircleImageView>(R.id.wlrvlf)
        wlrvlfButton.setOnClickListener {
            // 클릭 이벤트 발생 시 실행되는 코드
            val intent = Intent(this, commentActivity::class.java)
            startActivity(intent)
        }

    }

    private fun inRecycler(){
        profileAdapter = UserlistAdapter(this)

        cum.adapter = profileAdapter

        cum.layoutManager = LinearLayoutManager(this)
        profileAdapter.list.apply {
            add(Userlist(imageId = R.drawable.w, name = "muyngsu", lastMessage = "아휴...일하기싫어", lastMsgtime = "7:00 pm"))
            add(Userlist(imageId = R.drawable.bf74d248a214c24b, name = "junsung", lastMessage = "아..뭐라는거야~~", lastMsgtime = "7:00 pm"))
            add(Userlist(imageId = R.drawable.dbe26e25c44b5ae7, name = "jastess", lastMessage = "어...?!", lastMsgtime = "9:00 am"))
            add(Userlist(imageId = R.drawable.w, name = "muyngsu", lastMessage = "아휴...일하기싫어", lastMsgtime = "7:00 pm"))
            add(Userlist(imageId = R.drawable.bf74d248a214c24b, name = "junsung", lastMessage = "아..뭐라는거야~~", lastMsgtime = "7:00 pm"))
            add(Userlist(imageId = R.drawable.dbe26e25c44b5ae7, name = "jastess", lastMessage = "어...?!", lastMsgtime = "9:00 am"))
            add(Userlist(imageId = R.drawable.w, name = "muyngsu", lastMessage = "아휴...일하기싫어", lastMsgtime = "7:00 pm"))
            add(Userlist(imageId = R.drawable.bf74d248a214c24b, name = "junsung", lastMessage = "아..뭐라는거야~~", lastMsgtime = "7:00 pm"))
            add(Userlist(imageId = R.drawable.dbe26e25c44b5ae7, name = "jastess", lastMessage = "어...?!", lastMsgtime = "9:00 am"))

        }

        profileAdapter.notifyDataSetChanged()
    }
}
