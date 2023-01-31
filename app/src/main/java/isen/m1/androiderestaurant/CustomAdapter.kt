package isen.m1.androiderestaurant

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import isen.m1.androiderestaurant.databinding.CellCustomBinding

class CustomAdapter(val items: List<String>, val clickListenner: (Int) -> Unit):RecyclerView.Adapter<CustomAdapter.CellViewHolder>() {

	class CellViewHolder(binding: CellCustomBinding):RecyclerView.ViewHolder(binding.root){
		val textView = binding.itemName
		val root: ConstraintLayout = binding.root
	}


	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellViewHolder {
		val binding = CellCustomBinding.inflate(LayoutInflater.from(parent.context))
		return CellViewHolder(binding)
	}

	override fun onBindViewHolder(holder: CellViewHolder, position: Int) {
		holder.textView.text = items[position]
		holder.root.setOnClickListener {
			Log.d("button", "click sur un $position")
			clickListenner(position)
		}

	}

	override fun getItemCount(): Int {
		return items.count()
	}

}