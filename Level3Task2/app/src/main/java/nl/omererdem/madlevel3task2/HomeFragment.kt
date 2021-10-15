package nl.omererdem.madlevel3task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    // Run necessary functions on view created
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        observeAddReminderResult()
    }

    private fun observeAddReminderResult() {
        setFragmentResultListener(PORTAL_KEY) {
            key, bundle ->
            bundle.getParcelable<Portal>(BUNDLE_KEY)?.let {
                portals.add(it)
                portalAdapter.notifyDataSetChanged()
            }
        }
    }

    // On initializing view, setup the recyclerview and touch helper for the portal cards
    private fun initViews() {
        rvPortals.layoutManager = GridLayoutManager(context, 2)
        rvPortals.adapter = portalAdapter

        createItemTouchHelper().attachToRecyclerView(rvPortals)
    }

    // Touch helper for the portal cards for a right swipe
    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Delete the card on swipe
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                portals.removeAt(position)
                portalAdapter.notifyDataSetChanged()
            }
        }
        return ItemTouchHelper(callback)
    }
}