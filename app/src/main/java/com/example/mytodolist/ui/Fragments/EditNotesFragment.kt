package com.example.mytodolist.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.mytodolist.Model.Notes
import com.example.mytodolist.R
import com.example.mytodolist.ViewModel.NotesViewModel
import com.example.mytodolist.databinding.FragmentEditNotesBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.Date


class EditNotesFragment : Fragment() {
    private val oldNotes by navArgs<EditNotesFragmentArgs>()
    private lateinit var binding: FragmentEditNotesBinding
    var priority:String = "1"
    val viewModel :NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditNotesBinding.inflate(layoutInflater,container,false)
       //show delete icon
        setHasOptionsMenu(true)
        // text set kar rha hain  jo,notes adaptor se aye hai
        binding.editTitle.setText(oldNotes.data.title)
        binding.editSubtitle.setText(oldNotes.data.subTitle)
        binding.editNotes.setText(oldNotes.data.notes)

       when(oldNotes.data.priority){
           "1"-> {
               //1 ->green
               priority = "1"
               binding.prGreen.setImageResource(R.drawable.baseline_done_outline_24)
               binding.prRed.setImageResource(0)
               binding.prYellow.setImageResource(0)
           }

           "2"->{
               //2 ->red

               priority="2"
               binding.prRed.setImageResource(R.drawable.baseline_done_outline_24)
               binding.prGreen.setImageResource(0)
               binding.prYellow.setImageResource(0)
           }

           "3"-> {
               //3->yellow
               priority="3"
               binding.prYellow.setImageResource(R.drawable.baseline_done_outline_24)
               binding.prGreen.setImageResource(0)
               binding.prRed.setImageResource(0)
           }
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
        binding.btnEditSaveNotes.setOnClickListener{
            updateNotes(it)
        }

        return binding.root
    }

    private fun updateNotes(it: View?) {
        // get the data
        val title = binding.editTitle.text.toString()
        val subTitle = binding.editSubtitle.text.toString()
        val notes = binding.editNotes.text.toString()
        val d: Date = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy ", d.time)
        //Log.e("@@@","create Notes : $notesDate")

        // Create a Notes object with the gathered data
        val data = Notes(
            oldNotes.data.id,
            title = title,
            subTitle = subTitle,
            notes = notes,
            date = notesDate.toString(),
            priority
        )
        // Add the note using the ViewModel
        viewModel.updateNotes(data)
        Toast.makeText(context, "Notes Updated Successfully", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(it!!).navigate(R.id.action_editNotesFragment_to_homeFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
           // Log.e("@@@","OnOptionsItemSelected :Delete")
            val bottomsheet:BottomSheetDialog = BottomSheetDialog(requireContext(),R.style.BottomSheetStyle)
            bottomsheet.setContentView(R.layout.dialog_delete)

            val textViewYes = bottomsheet.findViewById<TextView>(R.id.dialog_yes)
            val textViewNo = bottomsheet.findViewById<TextView>(R.id.dialog_no)

            textViewYes?.setOnClickListener{
                viewModel.deleteNotes(oldNotes.data.id!!)
                 //Log.e("@@@","OnOptionsItemSelected :Delete")

                bottomsheet.dismiss()
               // Navigation.findNavController(it).navigate(R.id.action_editNotesFragment_to_homeFragment)

            }
            textViewNo?.setOnClickListener{
                bottomsheet.dismiss()
            }
            bottomsheet.show()
        }
        return super.onOptionsItemSelected(item)
    }
}