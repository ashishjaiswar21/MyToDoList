package com.example.mytodolist.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.mytodolist.Model.Notes
import com.example.mytodolist.R
import com.example.mytodolist.ViewModel.NotesViewModel
import com.example.mytodolist.databinding.FragmentCreateNotesBinding
import java.util.Date

class CreateNotesFragment : Fragment() {
    private lateinit var binding:FragmentCreateNotesBinding
    var priority:String = "1"
    val viewModel:NotesViewModel by viewModels()

    // Override onCreateView method to set up the fragment's view
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding =FragmentCreateNotesBinding.inflate(layoutInflater,container,false)

        binding.prGreen.setImageResource(R.drawable.baseline_done_outline_24)
        binding.btnSaveNotes.setOnClickListener{
                createNotes(it)
        }
        binding.prGreen.setOnClickListener{
            priority = "1"
            binding.prGreen.setImageResource(R.drawable.baseline_done_outline_24)
            binding.prRed.setImageResource(0)
            binding.prYellow.setImageResource(0)
        }
        binding.prRed.setOnClickListener{
            priority="2"
            binding.prRed.setImageResource(R.drawable.baseline_done_outline_24)
            binding.prGreen.setImageResource(0)
            binding.prYellow.setImageResource(0)
        }
        binding.prYellow.setOnClickListener{
            priority="3"
            binding.prYellow.setImageResource(R.drawable.baseline_done_outline_24)
            binding.prGreen.setImageResource(0)
            binding.prRed.setImageResource(0)
        }

        return binding.root
        }

    private fun createNotes(it: View?) {
        // get the data
        val title = binding.editTitle.text.toString()
        val subTitle = binding.editSubtitle.text.toString()
        val notes = binding.editNotes.text.toString()
        val d: Date = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy ", d.time)
        //Log.e("@@@","create Notes : $notesDate")

        // Create a Notes object with the gathered data
         val data = Notes(
             null,
             title = title,
             subTitle = subTitle,
             notes = notes,
             date = notesDate.toString(),
             priority
         )
        // Add the note using the ViewModel
        viewModel.addNotes(data)
        Toast.makeText(context, "Notes Created Successfully", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(it!!).navigate(R.id.action_createNotesFragment_to_homeFragment)
    }
}