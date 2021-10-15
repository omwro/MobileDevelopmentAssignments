package nl.omererdem.madlevel3task1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_rating.*

val games = listOf(
    "Minecraft",
    "Fortnite",
    "Counter Strike",
    "Rainbow 6 Siege",
    "Among us"
)

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class RatingFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rating, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnToSummary.setOnClickListener {
            navigateToSummary()
        }

        showRandomAssessableGame()
    }

    private fun navigateToSummary() {
        val args = Bundle()
        args.putFloat(ARG_GAME_RATING, ratingBar.rating)
        args.putString(ARG_GAME_NAME, tvGame.text.toString())

        findNavController().navigate(R.id.action_ratingFragment_to_summaryFragment, args)
    }

    private fun showRandomAssessableGame() {
        tvGame.text = games.random()
    }
}