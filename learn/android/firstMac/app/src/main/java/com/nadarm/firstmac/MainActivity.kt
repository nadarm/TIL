package com.nadarm.firstmac

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("${this.javaClass.simpleName} - onCreate")

        tv.setOnClickListener {
            val intent = Intent(this, Main2Activity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        println("${this.javaClass.simpleName} - onStart")
    }

    override fun onResume() {
        super.onResume()
        println("${this.javaClass.simpleName} - onResume")
    }

    override fun onPause() {
        super.onPause()
        println("${this.javaClass.simpleName} - onPause")
    }

    override fun onStop() {
        super.onStop()
        println("${this.javaClass.simpleName} - onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("${this.javaClass.simpleName} - onDestroy")
    }
}
