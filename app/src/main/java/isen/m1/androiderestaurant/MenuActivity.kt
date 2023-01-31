package isen.m1.androiderestaurant


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

		makeRequest()

		showDatas()
	}


	private fun categoryName(): String {
		return when(currentCategory) {
			Category.STARTER -> getString(R.string.starter)
			Category.MAIN -> getString(R.string.main)
			Category.DESSERT -> getString(R.string.dessert)
		}
	}

	private fun makeRequest() {
		val queue = Volley.newRequestQueue(this)
		val params = JSONObject()
		params.put(NetworkConstants.idShopKey, 1)
		val request = JsonObjectRequest(
			Method.POST,
			NetworkConstants.BASE_URL,
			params,
			{ response ->
				Log.d("request", response.toString(2))
				ParseData(response.toString())
			},
			{ error ->
				Log.e("error", error.toString())
			}
		)
		queue.add(request)
		//showDatas()
	}

	private fun showDatas(category: isen.m1.androiderestaurant.network.Category) {
		binding.recyclerView.layoutManager = LinearLayoutManager(this)
		binding.recyclerView.adapter = CustomAdapter(listOf("1","2","3")) {
			val Intent = Intent(this, DetailActivity::class.java)
			startActivity(Intent)
		}

	}

	private fun ParseData(data: String) {
		val result = GsonBuilder().create().fromJson(data, MenuResult::class.java)
		val category = result.data.first{ it.name == categoryFilterKey()}
		//Log.d("request", "parsing")
		showDatas(category)
	}

	private fun categoryFilterKey(): String {
		when(currentCategory) {
			Category.STARTER -> "entrées"
			Category.MAIN -> "plats"
			Category.DESSERT -> "desserts"
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

