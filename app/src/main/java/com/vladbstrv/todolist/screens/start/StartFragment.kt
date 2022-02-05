package com.vladbstrv.todolist.screens.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.vladbstrv.todolist.APP
import com.vladbstrv.todolist.R
import com.vladbstrv.todolist.adapter.NoteAdapter
import com.vladbstrv.todolist.databinding.FragmentStartBinding
import com.vladbstrv.todolist.model.NoteModel


class StartFragment : Fragment() {

    lateinit var binding: FragmentStartBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this).get(StartViewModel::class.java)
        viewModel.initDatabase()
        recyclerView = binding.rvNotes
        adapter = NoteAdapter(object : OnItemViewClickListener {
            override fun OnItemViewClick(noteModel: NoteModel) {
                val bundle = Bundle()
                bundle.putSerializable("note", noteModel)
                findNavController().navigate(R.id.action_startFragment_to_detailFragment, bundle)
            }

        })
        recyclerView.adapter = adapter
        viewModel.getAllNotes().observe(viewLifecycleOwner) { listNotes ->
            adapter.setList(listNotes.asReversed())
        }

        binding.btnNext.setOnClickListener{
//            APP.navController.navigate(R.id.action_startFragment_to_addNoteFragment)
            findNavController().navigate(R.id.action_startFragment_to_addNoteFragment)
        }
    }


        fun clickNote(noteModel: NoteModel){
            val bundle = Bundle()
            bundle.putSerializable("note", noteModel)
            findNavController().navigate(R.id.action_startFragment_to_detailFragment, bundle)
        }

    interface OnItemViewClickListener {
        fun OnItemViewClick(noteModel: NoteModel)
    }

}