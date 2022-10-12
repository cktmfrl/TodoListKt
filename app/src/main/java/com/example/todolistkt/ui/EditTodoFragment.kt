package com.example.todolistkt.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.todolistkt.MainActivity
import com.example.todolistkt.R
import com.example.todolistkt.databinding.FragmentEditTodoBinding
import com.example.todolistkt.models.Todo
import kotlinx.coroutines.launch

class EditTodoFragment : Fragment() {
    val viewModel by viewModels<MainViewModel>()

    private var _binding: FragmentEditTodoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO ActionBar Title

        // TODO CalendarView

        binding.doneFab.setOnClickListener {
            val todo = Todo(binding.todoEditText.text.toString(), System.currentTimeMillis())

            lifecycleScope.launch {
                viewModel.db.todoDao().insert(todo)
                findNavController().popBackStack()
            }
        }

        // TODO 삭제
        binding.deleteFab.visibility = View.GONE
        binding.deleteFab.setOnClickListener {
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}