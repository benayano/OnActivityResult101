package com.example.testingapplication

import android.content.Intent
import android.content.Intent.ACTION_PICK
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.testingapplication.LoginActivity.Companion.RESULT


/**
 * this project contains tow activities one the MainActivity and the other
 *LogInActivity that simulates logging into an Activity that holds an answer.
 * The mainActivity can either call loginActivity or open the image gallery and return a reply.
 * By the answer one chooses the image to be the central image
 *
 */

class MainActivity : AppCompatActivity() {

    companion object{
        const val LOGIN_REQUEST_COD = 5
        const val IMAGE_PICK_CODE = 3
    }

    private val myButton by lazy { findViewById<Button>(R.id.btnMain) }
    private val sendButton by lazy { findViewById<Button>(R.id.btnSend) }
    private val chooseButton by lazy { findViewById<Button>(R.id.btnChoose) }
    private val imageView by lazy { findViewById<ImageView>(R.id.ivMain) }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myButton.setOnClickListener {
            goToLoginActivity()
        }
        chooseButton.setOnClickListener {
            launchGallery()
        }
        sendButton.setOnClickListener {
            sendThisImage()
        }
    }
    private fun launchGallery() {
        val intent = Intent().apply {
            this.action =ACTION_PICK
            this.type = "image/*"
            startActivityForResult(this, IMAGE_PICK_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (RESULT_OK == resultCode){
            when(requestCode){
                LOGIN_REQUEST_COD->onLoginResult(data)
                IMAGE_PICK_CODE ->imageFoolScreen(data)
            }
        }
    }

    private fun imageFoolScreen(data: Intent?){
        imageView.setImageURI(data?.data)
    }


//no good
    @RequiresApi(Build.VERSION_CODES.R)
    private fun sendThisImage(){
        val b = BitmapFactory.decodeResource(resources,R.drawable.ic_launcher_background)
       val d = MediaStore.Images.Media.insertImage(applicationContext.contentResolver,b,"nameOfImage" , "description");
        //val path = MediaStore.Images.Media.insertImage(contentResolver,b,"this title","jjj")
       // val uri = Uri.parse(path)
        val path = MediaStore.Images.Media.insertImage(contentResolver, b,"R.id.ivMain.toLong()","")
        val  uri = Uri.parse(path)

        val  intent = Intent().apply {
            action = Intent.ACTION_SEND
            type= "image/*"
            putExtra(Intent.EXTRA_STREAM,uri)
            startActivity(Intent.createChooser(this ,"share to:"))
        }
    }
//-----------------Login Activity -----------------------------
    private fun goToLoginActivity(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivityForResult(intent, LOGIN_REQUEST_COD)
    }
    private fun onLoginResult(data: Intent?){
        data?.let {
            val isSuccess =it.getBooleanExtra(RESULT,false)
            if (isSuccess){
                Toast.makeText(this,"11111111111111111", 4).show()
            }else{
                Toast.makeText(this,"22222222222222222", 4).show()
            }
        }
    }
}