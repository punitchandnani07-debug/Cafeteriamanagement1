package com.example.cafeteriamanagement

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MenuAdapter(
    private val items: MutableList<MenuItem>,
    private val onTotalChanged: (Int) -> Unit  // 👈 Ye lambda ka type hai
) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    inner class MenuViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvItemName)
        val tvPrice: TextView = view.findViewById(R.id.tvItemPrice)
        val tvQuantity: TextView = view.findViewById(R.id.tvQuantity)
        val btnAdd: Button = view.findViewById(R.id.btnAdd)
        val btnRemove: Button = view.findViewById(R.id.btnRemove)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_menu, parent, false)
        return MenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val item = items[position]
        holder.tvName.text = item.name
        holder.tvPrice.text = "₹${item.price}"
        holder.tvQuantity.text = item.quantity.toString()

        holder.btnAdd.setOnClickListener {
            item.quantity++
            holder.tvQuantity.text = item.quantity.toString()
            updateTotal()
        }

        holder.btnRemove.setOnClickListener {
            if (item.quantity > 0) {
                item.quantity--
                holder.tvQuantity.text = item.quantity.toString()
                updateTotal()
            }
        }
    }

    override fun getItemCount(): Int = items.size

    private fun updateTotal() {
        val total = items.sumOf { it.price * it.quantity }
        onTotalChanged(total)
    }
}
