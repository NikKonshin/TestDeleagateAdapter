package com.nikitakonshin.testdeleagateadapter.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nikitakonshin.testdeleagateadapter.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.container_for_fragment, RVFragment()).commit()
    }
}