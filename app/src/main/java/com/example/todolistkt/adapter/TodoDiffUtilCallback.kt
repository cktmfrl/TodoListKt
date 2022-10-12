package com.example.todolistkt.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.todolistkt.models.Todo

class TodoDiffUtilCallback : DiffUtil.ItemCallback<Todo>() {
    override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem.uid == newItem.uid
    }

    override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem == newItem
    }
}