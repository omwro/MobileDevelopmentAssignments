package nl.omererdem.madlevel3task2

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_portal.view.*

class PortalAdapter (private val portals: List<Portal>) : RecyclerView.Adapter<PortalAdapter.ViewHolder>() {

    // Main class that binds the object values to its view element and sets click listener
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun databind(portal: Portal) {
            // Bind portal data to element
            itemView.tvTitle.text = portal.title
            itemView.tvUrl.text = portal.url

            // Set on click lister on element to open the browser to its original url
            itemView.setOnClickListener {
                openBrowser(itemView.context, Uri.parse(portal.url))
            }
        }
    }

    // Defines the element
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PortalAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_portal, parent, false)
        )
    }

    // Binds the data to the right element
    override fun onBindViewHolder(holder: PortalAdapter.ViewHolder, position: Int) {
        holder.databind(portals[position])
    }

    // Gets total portal count
    override fun getItemCount(): Int {
        return portals.size
    }

    // Open a custom chrome browser tab with the provided url
    private fun openBrowser(context: Context, uri: Uri) {
        val customTabsIntent = CustomTabsIntent.Builder()
        customTabsIntent.build().launchUrl(context, uri)
    }
}