package isen.m1.androiderestaurant.network

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Price (
	@SerializedName("id") val id: Int,
	@SerializedName("id_pizza") val id_pizza: Int,
	@SerializedName("id_size") val id_size: Int,
	@SerializedName("price") val price: String,
	@SerializedName("update_date") val update_date: String,
	@SerializedName("create_date") val create_date: String,
	@SerializedName("size") val size: String


): Serializable