package isen.m1.androiderestaurant


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request.Method
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder
import isen.m1.androiderestaurant.databinding.ActivityMenuBinding
import isen.m1.androiderestaurant.network.MenuResult
import isen.m1.androiderestaurant.network.NetworkConstants
import org.json.JSONObject


enum class Category {STARTER, MAIN, DESSERT}

class MenuActivity : AppCompatActivity() {
	companion object{
		val extraKey = "extraKey"
	}

	private lateinit var currentCategory: Category

	private lateinit var binding: ActivityMenuBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMenuBinding.inflate(layoutInflater)
		setContentView(binding.root)

		val category = intent.getSerializableExtra(extraKey) as? Category

		currentCategory = category ?: Category.STARTER

		supportActionBar?.title = categoryName()

		//Créer un fichier json, si il existe déja on ajoute un item
		val cart = intent.getSerializableExtra("cart") as Cart

		makeRequest(cart)



		//showDatas()
	}


	private fun categoryName(): String {
		return when(currentCategory) {
			Category.STARTER -> getString(R.string.starter)
			Category.MAIN -> getString(R.string.main)
			Category.DESSERT -> getString(R.string.dessert)
		}
	}

	private fun makeRequest(cart: Cart) {
		val queue = Volley.newRequestQueue(this)
		val params = JSONObject()
		params.put(NetworkConstants.idShopKey, 1)
		val request = JsonObjectRequest(
			Method.POST,
			NetworkConstants.BASE_URL,
			params,
			{ response ->
				Log.d("request", response.toString(2))
				ParseData(response.toString(), cart)
			},
			{ error ->
				Log.e("error", error.toString())
			}
		)
		queue.add(request)
		//showDatas()
	}

	private fun showDatas(category: isen.m1.androiderestaurant.network.Category, cart: Cart) {
		binding.recyclerView.layoutManager = LinearLayoutManager(this)
		binding.recyclerView.adapter = CustomAdapter(category.items) {position ->
			val Intent = Intent(this, DetailActivity::class.java)
			intent.putExtra("cart", cart)
			startActivity(Intent)
		}

	}

	private fun ParseData(data: String, cart: Cart) {
		val result = GsonBuilder().create().fromJson(data, MenuResult::class.java)
		val category = result.data.first{ it.name == categoryFilterKey()}
		//Log.d("request", "parsing")
		showDatas(category, cart)
	}

	private fun categoryFilterKey(): String {
		return when(currentCategory) {
			Category.STARTER -> "Entrées"
			Category.MAIN -> "Plats"
			Category.DESSERT -> "Desserts"
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
		Log.d("LifeCycle", "MenuActivity onStart")
	}

	override fun onResume() {
		super.onResume()
		Log.d("LifeCycle", "MenuActivity onResume")
	}

	override fun onPause() {
		super.onPause()
		Log.d("LifeCycle", "MenuActivity onPause")
	}

	override fun onDestroy() {
		Log.d("LifeCycle", "MenuActivity onDestroy")
		super.onDestroy()
	}

}

