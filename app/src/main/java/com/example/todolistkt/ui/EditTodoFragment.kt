package com.example.todolistkt.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.todolistkt.databinding.FragmentEditTodoBinding
import com.example.todolistkt.models.Todo
import kotlinx.coroutines.launch
import java.util.*

class EditTodoFragment : Fragment() {
    val viewModel by viewModels<MainViewModel>()

    private var _binding: FragmentEditTodoBinding? = null
    private val binding get() = _binding!!

    val calendar = Calendar.getInstance()

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

        setCalendarView()
        setButton()
    }

    private fun setCalendarView() {
        binding.calendarView.setOnDateChangeListener { calendarView, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        }
    }

    private fun setButton() {
        binding.doneFab.setOnClickListener {
            val inputText = binding.todoEditText.text.toString()
            if (inputText.isEmpty()) {
                Toast.makeText(activity, "할 일을 입력해 주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val todo = Todo(inputText, calendar.time.time)

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