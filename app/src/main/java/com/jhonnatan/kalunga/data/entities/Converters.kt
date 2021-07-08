package com.jhonnatan.kalunga.data.entities

import androidx.room.TypeConverter
import java.util.*

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.data.entities
 * Created by Jhonnatan E. Zamudio P. on 28/06/2021 at 12:20 p. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}