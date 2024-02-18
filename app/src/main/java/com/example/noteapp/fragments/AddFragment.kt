package com.example.noteapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.example.noteapp.MainActivity
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentAddBinding
import com.example.noteapp.model.Note
import com.example.noteapp.viewmodel.NoteViewModel


class AddFragment : Fragment(R.layout.fragment_add),MenuProvider {

    private var addBinding : FragmentAddBinding? = null
    private val binding get() = addBinding!!

    private lateinit var noteViewModel: NoteViewModel

    private lateinit var addView: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        addBinding = FragmentAddBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this,viewLifecycleOwner, Lifecycle.State.RESUMED)
        noteViewModel = (activity as MainActivity).noteViewModel
        addView = view
    }

    private fun saveNote(view: View){
        val noteTitle = binding.addNoteTitle.text.toString().trim()
        val noteDecs = binding.addNoteDesc.text.toString().trim()
        if(noteTitle != null){
            val note = Note(0,noteTitle,noteDecs)
            noteViewModel.addNote(note)
            Toast.makeText(addView.context, "Đã Thêm Note", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack(R.id.homeFragment,false)
        }else{
            Toast.makeText(addView.context, "Nhập tiêu đề", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.menu_add_note,menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId){
            R.id.menuAdd -> {
                saveNote(addView)
                true
            }else -> false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        addBinding = null
    }

}