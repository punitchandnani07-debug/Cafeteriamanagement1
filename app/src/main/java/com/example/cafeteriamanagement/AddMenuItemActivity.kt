package com.example.cafeteriamanagement

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cafeteriamanagement.databinding.ActivityAddMenuItemBinding

class AddMenuItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddMenuItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMenuItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSaveItem.setOnClickListener {
            val name = binding.etItemName.text.toString().trim()
            val priceText = binding.etItemPrice.text.toString().trim()

            // 🧩 Validation
            if (name.isEmpty() || priceText.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 🧩 Just check if price is valid (optional)
            val price = priceText.toIntOrNull()
            if (price == null) {
                Toast.makeText(this, "Invalid price", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // ✅ Show success toast only (no data saving)
            Toast.makeText(this, "Item saved successfully ✅", Toast.LENGTH_SHORT).show()

            // Clear fields
            binding.etItemName.text?.clear()
            binding.etItemPrice.text?.clear()
        }
    }
}
