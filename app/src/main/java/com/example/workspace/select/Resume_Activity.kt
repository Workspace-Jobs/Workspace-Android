package com.example.workspace.select

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import com.example.workspace.R

class Resume_Activity : AppCompatActivity() {
    private lateinit var submitButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resume)

        submitButton = findViewById(R.id.submit_button)

        // 버튼 클릭 리스너 설정
        submitButton.setOnClickListener {
            // 제출되었습니다 메시지 표시
            Toast.makeText(this, "제출되었습니다", Toast.LENGTH_SHORT).show()
        }


    }
    fun onBackButtonClicked(view: View) {
        onBackPressed()
    }

}