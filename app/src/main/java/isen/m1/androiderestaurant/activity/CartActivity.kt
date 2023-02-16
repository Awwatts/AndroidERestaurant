package isen.m1.androiderestaurant.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import isen.m1.androiderestaurant.data.CartItem
import isen.m1.androiderestaurant.adapter.CartAdapter
import isen.m1.androiderestaurant.adapter.CartItemListener
import isen.m1.androiderestaurant.databinding.ActivityCartBinding
import java.io.File

class CartActivity : AppCompatActivity(), CartItemListener {
	private lateinit var binding: ActivityCartBinding
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityCartBinding.inflate(layoutInflater)
		setContentView(binding.root)

		supportActionBar?.title = "Panier"

		binding.CartTitle.text = "Panier"

		//On recupere le fichier json
		getCartItems(this)


		//bouton pour retourner a la page d'accueil
		binding.returnButton.setOnClickListener {
			val intent = Intent(this, HomeActivity::class.java)
			startActivity(intent)
		}


		//bouton pour passer la commande
		binding.commandeButton.setOnClickListener {
			val snackbar = Snackbar.make(binding.root, "Votre commande est passé", Snackbar.LENGTH_LONG)
			snackbar.show()
			//val intent = Intent(this, HomeActivity::class.java)
			//startActivity(intent)
			//finish()
		}


	}

	/*
	* Fonction qui recupere le fichier json et qui le transforme en tableau de json, et merge les elements du panier avec le meme nom
	* @param context : contexte de l'application
	*/
	fun getCartItems(context: Context){
		try {
			val file = File(context.filesDir, "data.json")
			//On verifie si le fichier existe, pour eviter une erreur
			if (file.exists()) {
				Log.d("CartActivity", "Le fichier existe dans le stockage externe")

				//On recupere le contenu du fichier
				val recuperation = File(context.filesDir, "data.json").bufferedReader().readText();
				val gson = Gson()

				//On transforme le contenu du fichier en un tableau de json
				val itemsJsonArray = JsonParser().parse(recuperation).asJsonArray
				val cartItems = gson.fromJson<List<CartItem>>(recuperation, object : TypeToken<List<CartItem>>() {}.type)


				Log.d("cart", "JSON "+itemsJsonArray.toString())

				// Merge les éléments du panier avec le même nom, on ajoute les quantités et les prix
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

				// Affiche les éléments du panier
				binding.listCart.layoutManager = LinearLayoutManager(this)
				binding.listCart.adapter = CartAdapter(mergedCartItems)

			} else {
				Log.d("CartActivity", "Le fichier n'existe pas dans le stockage externe")
			}

		} catch (e: Exception) {
			Log.e("CartActivity", "Erreur de lecture du fichier JSON : ${e.message}")
		}
	}

	override fun onDeleteClick(cartItem: CartItem) {
		// Supprimer l'élément du panier et mettre à jour l'interface utilisateur
	}


}
