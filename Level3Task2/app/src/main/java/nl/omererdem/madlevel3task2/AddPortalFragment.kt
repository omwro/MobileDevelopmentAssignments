package nl.omererdem.madlevel3task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_add_portal.*

const val PORTAL_KEY = "portal_key"
const val BUNDLE_KEY = "bundle_key"

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddPortalFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_portal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Add portal on button click
        btnAddPortal.setOnClickListener {
            onAddPortal()
        }
    }

    // Add portal to the state and navigate back, else show an error snackbar
    private fun onAddPortal() {
        val titleTxt = etTitle.text.toString()
        val urlTxt = etUrl.text.toString()
        if (titleTxt.isNotBlank() && urlTxt.isNotBlank()) {
            setFragmentResult(PORTAL_KEY, bundleOf(Pair(BUNDLE_KEY, Portal(titleTxt, urlTxt))))
//            portals.add(Portal(titleTxt, urlTxt))
//            portalAdapter.notifyDataSetChanged()
//            findNavController().navigate(R.id.action_addPortalFragment_to_homeFragment)
            findNavController().popBackStack()
        } else {
            Snackbar.make(etTitle, getString(R.string.invalid_field), Snackbar.LENGTH_LONG).show()
        }
    }
}