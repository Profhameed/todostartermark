package com.commonsware.todo.repo

import androidx.room.TypeConverter
import java.time.Instant

class TypeTransmogrifier {
    @TypeConverter
    fun fromInstant(date: Instant?): Long? = date?.toEpochMilli()

    @TypeConverter
    fun toInstant(epochMilli: Long?): Instant? = epochMilli?.let {
        Instant.ofEpochMilli(it)
    }
}