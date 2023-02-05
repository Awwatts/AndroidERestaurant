package isen.m1.androiderestaurant

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import isen.m1.androiderestaurant.network.Ingredient
import isen.m1.androiderestaurant.databinding.CellDetailBinding


class DetailAdaptater(val items: List<Ingredient>, val clickListenner: (Int) -> Unit):
	RecyclerView.Adapter<DetailAdaptater.DetailViewHolder>() {
	class DetailViewHolder(val binding: CellDetailBinding): RecyclerView.ViewHolder(binding.root) {
		val textView: TextView = binding.Ingredients
		val root: ConstraintLayout = binding.root
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
		val binding = CellDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return DetailViewHolder(binding)
	}

	override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
		val ItemsViewModel = items[position]

		holder.textView.text = ItemsViewModel.name

		holder.root.setOnClickListener {
			Log.d("button", "click sur un $position")
			clickListenner(position)
		}

	}

	override fun getItemCount(): Int {
		return items.count()
	}

}


