package com.themobilecompany.madlevel5task2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.themobilecompany.madlevel5task2.R
import com.themobilecompany.madlevel5task2.model.NoteViewmodel
import kotlinx.android.synthetic.main.fragment_start.*
import kotlinx.android.synthetic.main.item_note.*
import kotlinx.android.synthetic.main.item_note.tvTitle

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class StartFragment : Fragment() {
    private val viewModel: NoteViewmodel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeAddNoteResult()
    }

    private fun observeAddNoteResult() {
        viewModel.note.observe(viewLifecycleOwner, Observer { note ->
            note?.let {
                tvTitle.text = it.title
                tvLastUpdated.text = getString(R.string.last_updated, it.lastUpdated.toString())
                tvNote.text = it.text
            }
        })
    }
}