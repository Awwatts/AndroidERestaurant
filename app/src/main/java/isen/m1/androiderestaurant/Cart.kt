package isen.m1.androiderestaurant

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Cart () : Serializable {
	private val items: MutableList<CartItem> = mutableListOf()

	fun addItem(item: CartItem) {
		items.add(item)
	}

	fun removeItem(item: CartItem) {
		items.remove(item)
	}

	fun clear() {
		items.clear()
	}

	fun getItems(): List<CartItem> {
		return items
	}
}
