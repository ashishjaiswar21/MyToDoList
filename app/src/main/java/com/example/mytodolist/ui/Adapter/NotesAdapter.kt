package com.example.mytodolist.ui.Adapter

//import com.example.mytodolist.ui.home.HomeFragmentDirections
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodolist.Model.Notes
import com.example.mytodolist.R
import com.example.mytodolist.databinding.ItemNotesBinding
import com.example.mytodolist.ui.Fragments.HomeFragmentDirections

class NotesAdapter(val requireContext: Context, private var notesList:List<Notes>):
RecyclerView.Adapter<NotesAdapter.notesViewHolder>()  {

    //for filtering seraching
    fun filtering(newFilteredList:ArrayList<Notes>){
        notesList = newFilteredList
        notifyDataSetChanged()
    }

    class notesViewHolder (val binding:ItemNotesBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
        return notesViewHolder(
            ItemNotesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {
        val data = notesList[position]
        holder.binding.notesTitle.text = data.title
        holder.binding.notesSubtitle.text = data.subTitle
        holder.binding.notesDate.text = data.date

        when(data.priority){
            "1"->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.green_dot)
            }
            "2"->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.red_dot)
            }
            "3"->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.yellow_dot)

            }
        }
        // clicking on each card item in recycler view
        holder.binding.root.setOnClickListener{
            // sending data from home fragment to edit notes frgm
            val action = HomeFragmentDirections.actionHomeFragmentToEditNotesFragment(data)
            Navigation.findNavController(it).navigate(action)

        }

    }

}