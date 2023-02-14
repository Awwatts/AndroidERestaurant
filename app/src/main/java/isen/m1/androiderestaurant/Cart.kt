package isen.m1.androiderestaurant

class Cart {
	private val items = mutableListOf<CartItem>()

	fun addItem(item: CartItem) {
		val existingItem = items.find { it.name == item.name }
		if (existingItem != null) {
			existingItem.quantity += item.quantity
		} else {
			items.add(item)
		}
	}

	fun removeItem(item: CartItem) {
		items.remove(item)
	}

	fun getTotalPrice(): Double {
		return items.sumOf { it.price * it.quantity }
	}

	fun getItems(): List<CartItem> {
		return items.toList()
	}
}
