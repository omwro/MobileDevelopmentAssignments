package nl.OmerErdem.madlvl5t2.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_game_backlog.*
import nl.OmerErdem.madlvl5t2.model.Game
import nl.OmerErdem.madlvl5t2.model.GameViewModel
import nl.OmerErdem.madlvl5t2.utils.GameAdapter
import nl.omererdem.madlvl5t2.R

// List of the games in the history
val games = arrayListOf<Game>()

// Adapter for the game object
val gameAdapter = GameAdapter(games)

class GameBacklogFragment : Fragment() {
    private val viewModel: GameViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_backlog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        retrieveGames()
    }

    // On init Fragment view
    private fun initView(view: View) {
        // Set the toolbar values
        val activity = (activity as MainActivity)
        activity.setTitle("Game Backlog")
        activity.enableBackButton(false)
        activity.enableDelete(true)

        // Set the adapter layout
        rvGames.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvGames.adapter = gameAdapter

        // Enable Swipe to Delete
        createItemTouchHelper().attachToRecyclerView(rvGames)

        // Click listener for the add button
        view.findViewById<FloatingActionButton>(R.id.fabAddGame).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    // Retrieve games from the database
    private fun retrieveGames() {
        viewModel.games.observe(viewLifecycleOwner, {
                savedGames ->
            games.clear()
            games.addAll(savedGames)
            games.sortBy { it.releaseDate }
            gameAdapter.notifyDataSetChanged()
        })
    }

    // Touch listener for game swipes to Left to delete
    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                viewModel.deleteGame(games[position])
            }
        }
        return ItemTouchHelper(callback)
    }
}