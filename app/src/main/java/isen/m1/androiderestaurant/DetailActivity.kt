package isen.m1.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import isen.m1.androiderestaurant.databinding.ActivityDetailBinding
import isen.m1.androiderestaurant.network.Ingredient
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

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

			val item = CartItem(name, price, 1)
			cart.addItem(item)

			val gson = Gson()
			val json = gson.toJson(cart.getItems())

			//val jsonString = gson.toJson(json)

			val file = File(applicationContext.filesDir, "data.json")
			file.writeText(json)


			//val filename = "data.json"
			//val file2 = File(getExternalFilesDir(null), filename)

			if (file.exists()) {
				Log.d("DEBUG", "Le fichier existe dans le stockage externe")
			} else {
				Log.d("DEBUG", "Le fichier n'existe pas dans le stockage externe")
			}


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