package com.vladbstrv.todolist.screens.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.vladbstrv.todolist.R
import com.vladbstrv.todolist.databinding.FragmentDetailBinding
import com.vladbstrv.todolist.model.NoteModel

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    lateinit var currentNote: NoteModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        currentNote = arguments?.getSerializable("note") as NoteModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        binding.tvTitleDetail.text = currentNote.title
        binding.tvTitleDetail.text = currentNote.description

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_startFragment)
        }

        binding.btnDelete.setOnClickListener {
            viewModel.delete(currentNote){}
            findNavController().navigate(R.id.action_detailFragment_to_startFragment)
        }
    }

}