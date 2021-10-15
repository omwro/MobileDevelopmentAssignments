package nl.OmerErdem.madlvl5t2.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_game.view.*
import nl.OmerErdem.madlvl5t2.model.Game
import nl.omererdem.madlvl5t2.R
import java.time.ZoneId

class GameAdapter(private val games: List<Game>) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun databind(game: Game) {
            itemView.tvTitle.text = game.title
            itemView.tvPlatform.text = game.platform
            val date = game.releaseDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
            itemView.tvReleaseDate.text = itemView.resources.getString(R.string.release,"${date.dayOfMonth} ${date.month} ${date.year}")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(games[position])
    }

    override fun getItemCount(): Int {
        return games.size
    }
}