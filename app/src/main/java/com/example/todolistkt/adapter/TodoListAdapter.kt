package com.example.todolistkt.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.todolistkt.databinding.ItemTodoBinding
import com.example.todolistkt.models.Todo

class TodoListAdapter(private val onClick: (Todo) -> Unit) :
    ListAdapter<Todo, TodoViewHolder>(TodoDiffUtilCallback()) {

    private lateinit var binding: ItemTodoBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = getItem(position)
        holder.bind(todo)
        holder.setOnClickListener(todo)
    }

}