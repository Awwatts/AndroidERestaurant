package isen.m1.androiderestaurant.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import isen.m1.androiderestaurant.data.Cart
import isen.m1.androiderestaurant.data.CartItem
import isen.m1.androiderestaurant.R
import isen.m1.androiderestaurant.databinding.ActivityDetailBinding
import isen.m1.androiderestaurant.data.Ingredient
import java.io.File

class DetailActivity() : AppCompatActivity() {
	private lateinit var binding: ActivityDetailBinding
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityDetailBinding.inflate(layoutInflater)
		setContentView(binding.root)

		supportActionBar?.title = "recette"
		val ingredients = intent.getSerializableExtra("ingredients") as List<Ingredient>

		binding.PlateName.text = intent.getStringExtra("name")
		binding.IngredientList.text = ingredients.joinToString(separator = " | ") { it.name }
		//binding.PlateImage.setImageResource(intent.getIntExtra("image", 0))

		Log.d("image",intent.getStringExtra("image_id").toString())

		//Affichage de l'image et test si il y a bien une image
		val imageUrl = intent.getStringExtra("image_id")
		val imageView = binding.PlateImage

		if (imageUrl != "") {
			Picasso.get().load(imageUrl).into(imageView)
		} else {
			imageView.setImageResource(R.drawable.notfound)
		}

		binding.addCart.text = "Add for " + intent.getStringExtra("price") + "€"

		val price = intent.getStringExtra("price").toString().toDouble()
		val name = intent.getStringExtra("name").toString()


		val cart = Cart()
		binding.addCart.setOnClickListener {

			// Ajouter un élément au panier
			val item = CartItem(name, price, 1)
			cart.addItem(item)

			// Créer un fichier JSON pour stocker les données du panier de l'utilisateur dans le stockage interne
			val gson = Gson()
			val json = gson.toJson(cart.getItems())
			val file = File(applicationContext.filesDir, "data.json")
			file.writeText(json)

			/*
			if (file.exists()) {
				Log.d("DEBUG", "Le fichier existe dans le stockage externe")
			} else {
				Log.d("DEBUG", "Le fichier n'existe pas dans le stockage externe")
			}*/


			// Afficher un message à l'utilisateur
			// Créer une instance de Snackbar
			val snackbar = Snackbar.make(binding.root, "plat ajouté au panier", Snackbar.LENGTH_SHORT)

			// Ajouter un bouton "Annuler" pour permettre à l'utilisateur de supprimer l'article ajouté au panier
			snackbar.setAction("Annuler") {
				cart.removeItem(item)  // Supprimer l'article ajouté du panier
			}
			// Afficher la Snackbar
			snackbar.show()


			Log.d("cart", cart.getItems().toString())

		}
	}

	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		menuInflater.inflate(R.menu.menu_main, menu)
		return true
	}
	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		return when (item.itemId) {
			R.id.cart_icon -> {
				// Rediriger vers le panier de l'utilisateur
				val Intent = Intent(this, CartActivity::class.java)
				//intent.putExtra("cart", cart)
				startActivity(Intent)
				true
			}
			else -> super.onOptionsItemSelected(item)
		}
	}


	override fun onStart() {
		super.onStart()
		Log.d("LifeCycle", "MainActivity onStart")
	}

	override fun onResume() {
		super.onResume()
		Log.d("LifeCycle", "MainActivity onResume")
	}

	override fun onPause() {
		super.onPause()
		Log.d("LifeCycle", "MainActivity onPause")
	}

	override fun onDestroy() {
		Log.d("LifeCycle", "MainActivity onDestroy")
		super.onDestroy()
	}
}