package com.example.todolistkt.adapter

import java.text.SimpleDateFormat
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistkt.databinding.ItemTodoBinding
import com.example.todolistkt.models.Todo
import java.util.*

class TodoViewHolder(
    private val binding: ItemTodoBinding,
    private val onClick: (Todo) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(todo: Todo) {
        val format = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()) // HH:mm:ss
        val text = format.format(Date(todo.date))
        binding.dateTextView.text = text
        binding.titleTextView.text = todo.title
    }

    fun setOnClickListener(todo: Todo) {
        binding.root.setOnClickListener {
            onClick(todo)
        }
    }
}