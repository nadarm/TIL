package com.example.helloworld

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val textView = findViewById<TextView>(R.id.myText)
        textView.text = "Hello World textView change"

        val dogList = arrayListOf<Dog>(
            Dog("Chow Chow", "Male", "4", "dog00"),
            Dog("Breed Pomeranian", "Female", "1", "dog01"),
            Dog("Golden Retriver", "Female", "3", "dog02"),
            Dog("Yorkshire Terrier", "Male", "5", "dog03"),
            Dog("Pug", "Male", "4", "dog04"),
            Dog("Alaskan Malamute", "Male", "7", "dog05"),
            Dog("Chow Chow", "Male", "4", "dog00"),
            Dog("Breed Pomeranian", "Female", "1", "dog01"),
            Dog("Golden Retriver", "Female", "3", "dog02"),
            Dog("Yorkshire Terrier", "Male", "5", "dog03"),
            Dog("Pug", "Male", "4", "dog04"),
            Dog("Alaskan Malamute", "Male", "7", "dog05"),
            Dog("Chow Chow", "Male", "4", "dog00"),
            Dog("Breed Pomeranian", "Female", "1", "dog01"),
            Dog("Golden Retriver", "Female", "3", "dog02"),
            Dog("Yorkshire Terrier", "Male", "5", "dog03"),
            Dog("Pug", "Male", "4", "dog04"),
            Dog("Alaskan Malamute", "Male", "7", "dog05"),
            Dog("Chow Chow", "Male", "4", "dog00"),
            Dog("Breed Pomeranian", "Female", "1", "dog01"),
            Dog("Golden Retriver", "Female", "3", "dog02"),
            Dog("Yorkshire Terrier", "Male", "5", "dog03"),
            Dog("Pug", "Male", "4", "dog04"),
            Dog("Alaskan Malamute", "Male", "7", "dog05"),
            Dog("Shih Tzu", "Female", "5", "dog06")
        )

//        val dogAdapter = Main2ListAdapter(this, dogList)
        val dogAdapter = MyRecyclerViewAdapter(this, dogList)
        val layoutManager = LinearLayoutManager(this)
        mainListView.layoutManager = layoutManager
        mainListView.adapter = dogAdapter


        val fm: FragmentManager = supportFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()
        ft.add(R.id.main2_fragment, MyFragment())
        ft.commit()
    }


}
