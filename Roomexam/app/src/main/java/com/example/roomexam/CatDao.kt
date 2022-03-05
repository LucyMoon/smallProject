package com.example.roomexam

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface CatDao {
    @Query("SELECT * FROM cat")
    fun getAll(): List<Cat>

    @Insert(onConflict = REPLACE)
    fun insert(cat : Cat)

    @Delete
    fun delete(cat : Cat)
}

