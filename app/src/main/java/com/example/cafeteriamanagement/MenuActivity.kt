package com.example.cafeteriamanagement

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cafeteriamanagement.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding
    private lateinit var adapter: MenuAdapter
    private var totalAmount = 0
    private lateinit var menuItems: MutableList<MenuItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Static menu list 🍽️
        menuItems = mutableListOf(
            MenuItem("1", "🫔 Dosa", 80, 0),
            MenuItem("2", "🍰 Pastry", 60, 0),
            MenuItem("3", "🍛 Chawli", 90, 0),
            MenuItem("4", "☕ Coffee", 50, 0),
            MenuItem("5", "🍔 Vada Pav", 40, 0)
        )

        // RecyclerView setup
        adapter = MenuAdapter(menuItems) { total ->
            totalAmount = total
            binding.tvTotal.text = "Total: ₹$totalAmount"
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // 🛒 Place Order click
        binding.btnOrder.setOnClickListener {
            // Create a summary of selected items
            val summary = menuItems
                .filter { it.quantity > 0 }
                .joinToString("\n") { "${it.name} x ${it.quantity} = ₹${it.price * it.quantity}" }

            if (summary.isEmpty()) {
                binding.tvTotal.text = "⚠️ Please select at least one item!"
                return@setOnClickListener
            }

            // Navigate to ConfirmOrderActivity
            val intent = Intent(this, ConfirmOrderActivity::class.java)
            intent.putExtra("ORDER_SUMMARY", summary)
            intent.putExtra("TOTAL", totalAmount)
            startActivity(intent)
        }
    }
}
