package isen.m1.androiderestaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import isen.m1.androiderestaurant.databinding.CellCartAdaptaterBinding

class CartAdapter(private val cartItems: List<CartItem>) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

	class CartViewHolder(binding: CellCartAdaptaterBinding) : RecyclerView.ViewHolder(binding.root) {
		val nameTextView: TextView = binding.textName
		val priceTextView: TextView = binding.textPrice
		val quantityTextView: TextView = binding.textQuantity
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.CartViewHolder {
		val binding = CellCartAdaptaterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return CartAdapter.CartViewHolder(binding)
	}


	override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
		val item = cartItems[position]
		holder.nameTextView.text = item.name
		holder.priceTextView.text = item.price.toString()
		holder.quantityTextView.text = item.quantity.toString()
	}

	override fun getItemCount() = cartItems.size
}
