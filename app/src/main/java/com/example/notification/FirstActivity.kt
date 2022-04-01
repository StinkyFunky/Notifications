package com.example.notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notification.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
