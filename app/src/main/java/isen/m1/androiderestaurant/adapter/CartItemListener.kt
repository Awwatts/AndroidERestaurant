package isen.m1.androiderestaurant.adapter

import isen.m1.androiderestaurant.data.CartItem

interface CartItemListener {
	fun onDeleteClick(cartItem: CartItem)
}