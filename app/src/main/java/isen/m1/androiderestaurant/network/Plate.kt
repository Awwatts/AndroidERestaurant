package isen.m1.androiderestaurant.network

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Plate (
	@SerializedName("name_fr") val name: String,
	@SerializedName("price") val price: List<Price>,
	@SerializedName("ingredients") val ingerdients: List<Ingredient>,
	@SerializedName("image") val image: List<String>


): Serializable