package com.example.cafeteriamanagement

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cafeteriamanagement.databinding.ActivityAdminBinding

class AdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 👉 Add Menu Item button click
        binding.btnAddMenuItem.setOnClickListener {
            val intent = Intent(this, AddMenuItemActivity::class.java)
            startActivity(intent)
        }
    }
}
