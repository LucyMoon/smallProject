package com.example.memo

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface MemoDao {

    @Query("SELECT * FROM memo")
    fun getAll(): List<Memo>

    @Insert(onConflict = REPLACE)
    fun insert(memo: Memo)

    @Delete
    fun delete(memo: Memo)

    @Update
    fun update(memo: Memo)
}