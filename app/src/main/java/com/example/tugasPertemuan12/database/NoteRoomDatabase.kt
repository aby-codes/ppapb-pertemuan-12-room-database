package com.example.tugasPertemuan12.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteRoomDatabase : RoomDatabase() {
    abstract  fun noteDao():NoteDao
    companion object {
        @Volatile
        private var instance: NoteRoomDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context.applicationContext).also {
                instance = it
            }
        }


        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NoteRoomDatabase::class.java,
                "notedb"
            ).build()
    }
}