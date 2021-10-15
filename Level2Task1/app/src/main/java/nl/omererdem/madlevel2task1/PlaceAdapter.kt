package nl.omererdem.madlevel2task1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import nl.omererdem.madlevel2task1.databinding.ItemPlaceBinding

class PlaceAdapter(private val places: List<Place>) : RecyclerView.Adapter<PlaceAdapter.ViewHolder>() {

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemPlaceBinding.bind(itemView)

        fun databind(place: Place) {
            binding.placename.text = place.name
            binding.placeimage.setImageResource(place.imageResId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_place, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PlaceAdapter.ViewHolder, position: Int) {
        holder.databind(places[position])
    }

    override fun getItemCount(): Int {
        return places.size
    }
}