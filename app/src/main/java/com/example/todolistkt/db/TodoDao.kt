package com.example.todolistkt.db

import androidx.room.*
import com.example.todolistkt.models.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo ORDER BY date DESC")
    fun getAll(): Flow<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: Todo)

    @Update
    suspend fun update(entity: Todo)

    @Delete
    suspend fun delete(entity: Todo)

}