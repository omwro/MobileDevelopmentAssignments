package nl.omererdem.madlevel2task1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import nl.omererdem.madlevel2task1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // Frontend binding var
    private lateinit var binding: ActivityMainBinding

    // List of places
    private val places: ArrayList<Place> = arrayListOf()

    // places set to a placeAdapter
    private val placeAdapter = PlaceAdapter(places)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        // Set staggered layout to recyclerview
        binding.rvPlaces.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.rvPlaces.adapter = placeAdapter

        // Insert place names and images to the list
        for (i in Place.PLACE_NAMES.indices) {
            places.add(
                Place(Place.PLACE_NAMES[i],
                Place.PLACE_RES_DRAWABLE_IDS[i])
            )
        }

        // Notify system changes has been made
        placeAdapter.notifyDataSetChanged()
    }
}