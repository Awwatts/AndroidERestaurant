package isen.m1.androiderestaurant.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import isen.m1.androiderestaurant.data.CartItem
import isen.m1.androiderestaurant.R
import isen.m1.androiderestaurant.databinding.CellCartAdaptaterBinding

class CartAdapter(private var cartItems: List<CartItem>) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

	class CartViewHolder(binding: CellCartAdaptaterBinding) : RecyclerView.ViewHolder(binding.root) {
		val nameTextView: TextView = binding.textName
		val priceTextView: TextView = binding.textPrice
		val quantityTextView: TextView = binding.textQuantity

		val deleteButton: Button = itemView.findViewById(R.id.delete_button)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
		val binding = CellCartAdaptaterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return CartViewHolder(binding)
	}


	override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
		val filteredList = cartItems.filterIndexed { index, _ -> index != position }

		val item = cartItems[position]
		holder.nameTextView.text = item.name
		holder.priceTextView.text = item.price.toString()
		holder.quantityTextView.text = item.quantity.toString()
		holder.deleteButton.setOnClickListener {
			val position = holder.adapterPosition
			if (position != RecyclerView.NO_POSITION) {
				removeItem(position)
			}
		}


		//holder.bind(item, onDeleteClick)
	}

	override fun getItemCount() = cartItems.size

	/*
	 * Supprime un élément du panier
	 * @param position la position de l'élément à supprimer
	 */
	fun removeItem(position: Int) {
		cartItems = cartItems.filterIndexed { index, _ -> index != position }
		notifyItemRemoved(position)
	}



}



