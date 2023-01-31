package isen.m1.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import isen.m1.androiderestaurant.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity() {

	private lateinit var binding: ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		buttonListenner()
	}

	private fun buttonListenner() {
		binding.entreeButton.setOnClickListener {
			Log.d("button", "click sur button entree")
			Toast.makeText(this, "entrée", Toast.LENGTH_SHORT).show()
			val intent = Intent(this, MenuActivity::class.java)
			startActivity(intent)
			showCategory(isen.m1.androiderestaurant.Category.STARTER)



		}
		binding.platButton.setOnClickListener {
			Log.d("button", "click sur button plats")
			Toast.makeText(this, "Plat", Toast.LENGTH_SHORT).show()
			val intent = Intent(this, MenuActivity::class.java)
			startActivity(intent)
			showCategory(isen.m1.androiderestaurant.Category.MAIN)
		}
		binding.dessertButton.setOnClickListener {
			Log.d("button", "click sur button dessert")
			Toast.makeText(this, "Dessert", Toast.LENGTH_SHORT).show()
			val intent = Intent(this, MenuActivity::class.java)
			startActivity(intent)
			showCategory(isen.m1.androiderestaurant.Category.DESSERT)
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

	private fun showCategory(category:Category) {
		val intent = Intent(this, MenuActivity::class.java)
		intent.putExtra(MenuActivity.extraKey, category)
		startActivity(intent)
	}
}