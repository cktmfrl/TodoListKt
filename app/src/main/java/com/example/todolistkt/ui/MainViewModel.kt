package com.example.todolistkt.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.todolistkt.db.AppDatabase
import com.example.todolistkt.models.Todo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// AndroidViewModel은 액티비티와 수명을 같이 함
class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java, "todo-db"
    ).build()

    private val _todos = MutableStateFlow<List<Todo>>(emptyList())
    val todos: StateFlow<List<Todo>> = _todos

    var selectedItem: Todo? = null

    init {
        viewModelScope.launch {
            db.todoDao().getAll().collect {
                _todos.value = it
            }
        }
    }

    fun addTodo(text: String, date: Long) {
        viewModelScope.launch {
            db.todoDao().insert(Todo(text, date))
        }
    }

    fun updateTodo(text: String, date: Long) {
        selectedItem?.let { todo ->
            todo.apply {
                this.title = text
                this.date = date
            }

            viewModelScope.launch {
                db.todoDao().update(todo)
            }
            selectedItem = null
        }
    }

    fun deleteTodo(uid: Int) {
        _todos.value
            .find { todo -> todo.uid == uid }
            ?.let { todo ->
                viewModelScope.launch {
                    db.todoDao().delete(todo)
                }
                selectedItem = null
            }
    }

}