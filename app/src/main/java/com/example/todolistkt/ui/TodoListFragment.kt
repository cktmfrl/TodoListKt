package com.example.todolistkt.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolistkt.R
import com.example.todolistkt.adapter.TodoListAdapter
import com.example.todolistkt.databinding.FragmentTodoListBinding
import kotlinx.coroutines.flow.collectLatest

class TodoListFragment : Fragment() {
    private val viewModel by activityViewModels<MainViewModel>()

    private var _binding: FragmentTodoListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTodoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = TodoListAdapter { todo ->
            viewModel.selectedItem = todo
            findNavController().navigate(R.id.action_ListFragment_to_EditFragment)
        }
        binding.recyclerView.adapter = adapter

        lifecycleScope.launchWhenStarted {
            viewModel.todos.collectLatest {
                adapter.submitList(it)
            }
        }

        binding.addFab.setOnClickListener {
            findNavController().navigate(R.id.action_ListFragment_to_EditFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}