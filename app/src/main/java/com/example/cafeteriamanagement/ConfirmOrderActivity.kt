package com.example.cafeteriamanagement

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.cafeteriamanagement.databinding.ActivityConfirmOrderBinding

class ConfirmOrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConfirmOrderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val orderSummary = intent.getStringExtra("ORDER_SUMMARY") ?: "No items selected"
        val total = intent.getIntExtra("TOTAL", 0)

        binding.tvOrderSummary.text = orderSummary
        binding.tvTotalAmount.text = "Total: ₹$total"

        binding.btnConfirm.setOnClickListener {
            // Show success message ✅
            binding.tvOrderSummary.text = "✅ Order Confirmed!\nThank you for ordering!"
            binding.btnConfirm.isEnabled = false
            binding.btnConfirm.text = "Order Confirmed"

            // 2 seconds delay then go back to MenuActivity
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, MenuActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }, 2000) // 2000ms = 2 seconds
        }
    }
}
