package com.sinigr.usersapp.modules.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import com.sinigr.usersapp.R

import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}
