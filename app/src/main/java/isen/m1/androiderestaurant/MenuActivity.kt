package isen.m1.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import isen.m1.androiderestaurant.databinding.ActivityMenuBinding


enum class Category {STARTER, MAIN, DESSERT}

class MenuActivity : AppCompatActivity() {
	companion object{
		val extraKey = "extraKey"
	}

	private lateinit var binding: ActivityMenuBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMenuBinding.inflate(layoutInflater)
		setContentView(binding.root)

		val category = intent.getSerializableExtra(extraKey) as? Category

		supportActionBar?.title = categoryName(category ?: Category.STARTER)

		showDatas()
	}

	private fun showDatas() {
		binding.recyclerView.layoutManager = LinearLayoutManager(this)
		binding.recyclerView.adapter = CustomAdapter(listOf("1","2","3")) {
			val Intent = Intent(this, DetailActivity::class.java)
			startActivity(Intent)
		}

	}

	private fun categoryName(category: Category): String {
		return when(category) {
			Category.STARTER -> getString(R.string.starter)
			Category.MAIN -> getString(R.string.main)
			Category.DESSERT -> getString(R.string.dessert)
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

