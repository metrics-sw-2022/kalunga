package com.jhonnatan.kalunga.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jhonnatan.kalunga.data.user.entities.User
import com.jhonnatan.kalunga.data.user.source.UserDAO
import com.jhonnatan.kalunga.data.version.source.VersionDAO
import com.jhonnatan.kalunga.data.version.entities.Version

/****
 * Project: kalunga
 * From: com.jhonnatan.kalunga.data.source.local.databases
 * Created by Jhonnatan E. Zamudio P. on 28/06/2021 at 11:36 a. m.
 * More info:  https://venecambios-kalunga.com/
 * All rights reserved 2021.
 ****/

@Database(entities = [Version::class, User::class], version = 2)
@TypeConverters(Converters::class)
abstract class KalungaDB : RoomDatabase() {

    abstract fun versionDAO(): VersionDAO
    abstract fun userDAO(): UserDAO

    companion object {
        @Volatile
        private var INSTANCE: KalungaDB? = null

        fun getInstance(context: Context): KalungaDB {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        KalungaDB::class.java,
                        "kalunga_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}