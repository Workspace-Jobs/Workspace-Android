package com.example.workspace.component

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.workspace.R

class commentActivity : AppCompatActivity() {

    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)

        editText = findViewById(R.id.editText)

        // EditText에 포커스를 줄 때 키보드 위에 버튼이 나타나도록 설정
        editText.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                showButtonAboveKeyboard()
            } else {
                hideButtonAboveKeyboard()
            }
        }
    }

    private fun showButtonAboveKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun hideButtonAboveKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editText.windowToken, 0)
    }
}
