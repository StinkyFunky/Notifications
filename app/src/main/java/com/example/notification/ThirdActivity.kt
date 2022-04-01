package com.example.notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notification.databinding.ActivityThirdBinding


class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}