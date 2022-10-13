package com.example.todolistkt.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.todolistkt.databinding.FragmentEditTodoBinding
import java.util.*

class EditTodoFragment : Fragment() {
    val viewModel by activityViewModels<MainViewModel>()

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

        // TODO: ActionBar Title

        if (viewModel.selectedItem == null) {
            binding.deleteFab.visibility = View.GONE
        }

        viewModel.selectedItem?.let {
            binding.todoEditText.setText(it.title)
            binding.calendarView.date = it.date
        }

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
            if (binding.todoEditText.text.toString().isNotEmpty()) {
                if (viewModel.selectedItem != null) {
                    viewModel.updateTodo(binding.todoEditText.text.toString(), calendar.time.time)
                } else {
                    viewModel.addTodo(binding.todoEditText.text.toString(), calendar.time.time)
                }
                findNavController().popBackStack()
            }
        }

        binding.deleteFab.setOnClickListener {
            viewModel.deleteTodo(viewModel.selectedItem!!.uid)
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}