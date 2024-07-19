package com.example.mytodolist.ui.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mytodolist.Model.Notes
import com.example.mytodolist.R
import com.example.mytodolist.ViewModel.NotesViewModel
import com.example.mytodolist.databinding.FragmentHomeBinding
import com.example.mytodolist.ui.Adapter.NotesAdapter


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel:NotesViewModel by viewModels()
    private var oldMyNotes = arrayListOf<Notes>()
    private lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        setHasOptionsMenu(true)
        viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
//            for(i in notesList){ Log.e("@@@","OnCreateView : ${i.title}") }
            binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.rcvAllNotes.adapter = NotesAdapter(requireContext(), notesList)
        }
        binding.filterHigh.setOnClickListener{
            viewModel.getHighNotes().observe(viewLifecycleOwner) { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(),notesList)
                binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.rcvAllNotes.adapter = adapter
            }
        }
        binding.filterMedium.setOnClickListener{
            viewModel.getMediumNotes().observe(viewLifecycleOwner) { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>

                adapter = NotesAdapter(requireContext(),notesList)
                binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.rcvAllNotes.adapter = adapter
            }

        }
        binding.filterLow.setOnClickListener{
            viewModel.getLowNotes().observe(viewLifecycleOwner) { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>

                adapter = NotesAdapter(requireContext(),notesList)
                binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.rcvAllNotes.adapter = adapter }

        }
        binding.allNotes.setOnClickListener{
            viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(),notesList)
                binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.rcvAllNotes.adapter = adapter  }

        }
        binding.btnAddNotes.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_createNotesFragment)
        }
        return binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
       inflater.inflate(R.menu.search_menu,menu)

        val item = menu.findItem(R.id.app_bar_search)
        val searchView = item.actionView as SearchView
        searchView.queryHint = "Enter Notes Here...."
        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                // user pura text likhe fir submit kare fir jaake text aaye
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // jese user text search ke liye likhte jaye vse hi suggestion aate jaye
                  NotesFiltering(newText)
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }
    private fun NotesFiltering(newText: String?) {
        //Log.e("@@@","NotesFiltering : $newText")
        val newFilterList = arrayListOf<Notes>()
        for(i in oldMyNotes){
            if(i.title.contains(newText!!)||i.subTitle.contains(newText)){
                newFilterList.add(i)
            }
            adapter.filtering(newFilterList)
        }

    }

}