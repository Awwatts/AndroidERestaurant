package isen.m1.androiderestaurant

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import isen.m1.androiderestaurant.databinding.ActivityCartBinding
import isen.m1.androiderestaurant.databinding.ActivityMainBinding
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

class CartActivity : AppCompatActivity() {
	private lateinit var binding: ActivityCartBinding
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityCartBinding.inflate(layoutInflater)
		setContentView(binding.root)

		supportActionBar?.title = "Panier"

		binding.CartTitle.text = "Panier"


		getCartItems(this)

		binding.returnButton.setOnClickListener {
			val intent = Intent(this, HomeActivity::class.java)
			startActivity(intent)
		}

		val bindingMain = ActivityMainBinding.inflate(layoutInflater)
		val root = bindingMain.root
		val snackbar = Snackbar.make(root, "Votre commande est passé", Snackbar.LENGTH_LONG)


		binding.commandeButton.setOnClickListener {
			val snackbar = Snackbar.make(binding.root, "Votre commande est passé", Snackbar.LENGTH_LONG)
			snackbar.show()
			//val intent = Intent(this, HomeActivity::class.java)
			//startActivity(intent)
			//finish()
		}


	}

	fun getCartItems(context: Context){
		try {
			val file = File(context.filesDir, "data.json")
			if (file.exists()) {
				Log.d("CartActivity", "Le fichier existe dans le stockage externe")
				val recuperation = File(context.filesDir, "data.json").bufferedReader().readText();
				val gson = Gson()

				val itemsJsonArray = JsonParser().parse(recuperation).asJsonArray
				val cartItems = gson.fromJson<List<CartItem>>(recuperation, object : TypeToken<List<CartItem>>() {}.type)


				Log.d("cart", "JSON "+itemsJsonArray.toString())

				val cartItemsMap = HashMap<String, CartItem>()
				for (item in cartItems) {
					val name = item.name
					if (cartItemsMap.containsKey(name)) {
						val existingItem = cartItemsMap[name]
						existingItem?.quantity = existingItem?.quantity?.plus(item.quantity) ?: 0
						existingItem?.price = existingItem?.price?.plus(item.price) ?: 0.0
					} else {
						cartItemsMap[name] = item
					}
				}
				val mergedCartItems = cartItemsMap.values.toList()

				binding.listCart.layoutManager = LinearLayoutManager(this)
				binding.listCart.adapter = CartAdapter(mergedCartItems)

			} else {
				Log.d("CartActivity", "Le fichier n'existe pas dans le stockage externe")
			}

		} catch (e: Exception) {
			Log.e("CartActivity", "Erreur de lecture du fichier JSON : ${e.message}")
		}
	}


}
