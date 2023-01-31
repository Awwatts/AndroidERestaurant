package isen.m1.androiderestaurant.network

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Ingredient (
	@SerializedName("id") val id: Int,
	@SerializedName("id_shop") val id_shop: Int,
	@SerializedName("name_fr") val name_fr: String,
	@SerializedName("name_en") val name_en: String,
	@SerializedName("create_date") val create_date: String,
	@SerializedName("update_date") val update_date: String,
	@SerializedName("id_pizza") val id_pizza: Int

): Serializable