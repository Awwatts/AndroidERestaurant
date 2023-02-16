package isen.m1.androiderestaurant.data

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



