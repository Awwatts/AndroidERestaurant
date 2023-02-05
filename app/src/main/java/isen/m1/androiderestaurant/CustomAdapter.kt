package isen.m1.androiderestaurant

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import isen.m1.androiderestaurant.databinding.CellCustomBinding
import isen.m1.androiderestaurant.network.Plate


class CustomAdapter(val items: List<Plate>, val clickListenner: (Plate) -> Unit):RecyclerView.Adapter<CustomAdapter.CellViewHolder>() {

	class CellViewHolder(binding: CellCustomBinding):RecyclerView.ViewHolder(binding.root){
		val textView: TextView = binding.itemName
		val imageView: ImageView = binding.imageCell
		val price : TextView = binding.priceView
		val root: ConstraintLayout = binding.root
	}


	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellViewHolder {
		val binding = CellCustomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return CellViewHolder(binding)
	}

	override fun onBindViewHolder(holder: CellViewHolder, position: Int) {
		val ItemsViewModel = items[position]

		holder.textView.text = ItemsViewModel.name
		holder.price.text = ItemsViewModel.price[0].price + "€"

		if(ItemsViewModel.image[0] != "")
		{
			Picasso.get().load(ItemsViewModel.image[0]).into(holder.imageView)
		}
		holder.root.setOnClickListener {
			Log.d("button", "click sur un $position")
			clickListenner(items[position])
		}

	}

	override fun getItemCount(): Int {
		return items.count()
	}

}