package com.elconfidencial.bubbleshowcase.testapp

import android.os.Bundle
import androidx.core.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.elconfidencial.bubbleshowcase.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        supportFragmentManager.let { it1 ->
            FragmentTest().show(it1, null)
        }
    }



}
