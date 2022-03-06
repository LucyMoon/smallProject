package com.example.memo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Memo::class], version = 1)
abstract class MemoDB: RoomDatabase() {
    abstract fun memoDao(): MemoDao

    companion object {
        private var INSTANCE: MemoDB? = null

        fun getInstance(context: Context): MemoDB? {
            if(INSTANCE == null){
                kotlin.synchronized(MemoDB::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MemoDB::class.java,
                        "memo.db")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}