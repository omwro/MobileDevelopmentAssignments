package com.themobilecompany.madlevel5task2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.themobilecompany.madlevel5task2.R
import com.themobilecompany.madlevel5task2.model.NoteViewmodel
import kotlinx.android.synthetic.main.fragment_add_note.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddNoteFragment : Fragment() {
    private val viewModel: NoteViewmodel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSave.setOnClickListener {
            saveNote()
        }
        observeNote()
    }

    private fun observeNote() {
        viewModel.note.observe(viewLifecycleOwner, Observer { note ->
            note?.let {
                tilTitle.editText?.setText(it.title)
                tilNote.editText?.setText(it.text)
            }
        })

        viewModel.error.observe(viewLifecycleOwner, Observer { message ->
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        })

        viewModel.success.observe(viewLifecycleOwner, Observer { success ->
            findNavController().popBackStack()
        })
    }


    private fun saveNote() {
        viewModel.updateNote(tilTitle.editText?.text.toString(), tilTitle.editText?.text.toString())
    }
}