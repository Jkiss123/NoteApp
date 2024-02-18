package com.example.noteapp.fragments

import android.app.AlertDialog
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
import androidx.navigation.fragment.navArgs
import com.example.noteapp.MainActivity
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentEditBinding
import com.example.noteapp.model.Note
import com.example.noteapp.viewmodel.NoteViewModel


class EditFragment : Fragment(R.layout.fragment_edit),MenuProvider {
    private var editNoteBinding : FragmentEditBinding? = null
    private val binding get() = editNoteBinding!!
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var currentNote: Note

    private val args: EditFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        editNoteBinding = FragmentEditBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this,viewLifecycleOwner, Lifecycle.State.RESUMED)
        noteViewModel = (activity as MainActivity).noteViewModel
        currentNote = args.note!!

        binding.editNoteTitle.setText(currentNote.noteTitle)
        binding.editNoteDesc.setText(currentNote.noteDesc)

        binding.editNoteFab.setOnClickListener {
            val noteTitle = binding.editNoteTitle.text.toString().trim()
            val noteDecs =  binding.editNoteDesc.text.toString().trim()

            if(noteTitle != null){
                val note = Note(0,noteTitle,noteDecs)
                noteViewModel.updateNote(note)
                findNavController().popBackStack(R.id.homeFragment,false)
            }else{
                Toast.makeText(context,"Nhập lại tiêu đề ",Toast.LENGTH_LONG).show()
            }

        }
    }

    private fun deleteNote(){
        AlertDialog.Builder(activity).apply {
            setTitle("Delete Note")
            setMessage("Bạn có muốn xóa ghi nhớ không?")
            setPositiveButton("Delete"){_,_ ->
                noteViewModel.deleteNote(currentNote)
                Toast.makeText(context,"Đã Xóa",Toast.LENGTH_LONG).show()
                findNavController().popBackStack(R.id.homeFragment,false)
            }
            setNegativeButton("Cancel",null)
        }.create().show()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.menu_edit_note,menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId){
            R.id.deleteMenu ->{
                deleteNote()
                true
            }else -> false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        editNoteBinding = null
    }
}