package com.example.memo

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memo")
class Memo(@PrimaryKey(autoGenerate = true) var id: Long?,
           @ColumnInfo(name = "title") var title: String,
           @ColumnInfo(name = "text") var text: String
) {
    constructor(): this(null, "", "")
}