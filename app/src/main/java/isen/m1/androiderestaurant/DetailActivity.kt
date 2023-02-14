package isen.m1.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.squareup.picasso.Picasso
import isen.m1.androiderestaurant.databinding.ActivityDetailBinding
import isen.m1.androiderestaurant.network.Ingredient

class DetailActivity : AppCompatActivity() {
	private lateinit var binding: ActivityDetailBinding
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityDetailBinding.inflate(layoutInflater)
		setContentView(binding.root)

		supportActionBar?.title = "recette"
		val ingredients = intent.getSerializableExtra("ingredients") as List<Ingredient>

		binding.PlateName.text = intent.getStringExtra("name")
		binding.IngredientList.text = ingredients.joinToString(separator = " ") { it.name }
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