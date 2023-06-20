package com.example.workspace.select

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.workspace.R

class Resume_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resume)
    }
    fun onBackButtonClicked(view: View) {
        onBackPressed()
    }
}