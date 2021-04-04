package com.example.OnActivityResult

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoginActivity : AppCompatActivity() {
    companion object{
        const val   RESULT = "success"
    }

    private val success by lazy { findViewById<Button>(R.id.btnSuccess) }
    private val failed by lazy { findViewById<Button>(R.id.btnFails) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        success.setOnClickListener {
            val intent = Intent().apply {
                putExtra(RESULT,true)
            }
            setResult(RESULT_OK,intent)
            finish()
        }

        failed.setOnClickListener {
            val intent= Intent().apply {
                putExtra(RESULT,false)
            }
            setResult(RESULT_OK,intent)
            finish()
        }
    }
}