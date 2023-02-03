package isen.m1.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import isen.m1.androiderestaurant.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
	private lateinit var binding: ActivityDetailBinding
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityDetailBinding.inflate(layoutInflater)
		setContentView(binding.root)

		supportActionBar?.title = "recette"
	}

}