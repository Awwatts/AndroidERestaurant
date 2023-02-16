package isen.m1.androiderestaurant.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import isen.m1.androiderestaurant.data.Cart
import isen.m1.androiderestaurant.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity() {

	private lateinit var binding: ActivityMainBinding

	private val cart = Cart()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		buttonListenner(cart)


	}

	private fun buttonListenner(cart: Cart) {
		binding.entreeButton.setOnClickListener {
			Toast.makeText(this, "entrée", Toast.LENGTH_SHORT).show()
			showCategory(Category.STARTER, cart)
		}
		binding.platButton.setOnClickListener {
			//Log.d("button", "click sur button plats")
			Toast.makeText(this, "Plat", Toast.LENGTH_SHORT).show()
			/*val intent = Intent(this, MenuActivity::class.java)
			startActivity(intent)*/
			showCategory(Category.MAIN, cart)
		}
		binding.dessertButton.setOnClickListener {
			Toast.makeText(this, "Dessert", Toast.LENGTH_SHORT).show()
			showCategory(Category.DESSERT, cart)
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

	private fun showCategory(category: Category, cart: Cart){
		val intent = Intent(this, MenuActivity::class.java)
		intent.putExtra(MenuActivity.extraKey, category)
		intent.putExtra("cart", cart)
		startActivity(intent)
	}
}

