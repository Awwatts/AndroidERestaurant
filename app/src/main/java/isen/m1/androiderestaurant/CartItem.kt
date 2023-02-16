package isen.m1.androiderestaurant

/*class CartItem{
	//(val name: String, val price: Double)
	var name: String? = null
	var price: Double? = null

}*/
data class CartItem(
	val name: String,
	var price: Double,
	var quantity: Int
)



