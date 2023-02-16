package isen.m1.androiderestaurant.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Plate (
	@SerializedName("name_fr") val name: String,
	@SerializedName("prices") val price: List<Price>,
	@SerializedName("ingredients") val ingerdients: List<Ingredient>,
	@SerializedName("images") val image: List<String>


): Serializable