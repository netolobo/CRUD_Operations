package com.netoloboapps.crudoperations.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [LocalMotorcycle::class],
    version = 1,
    exportSchema = false
)
abstract class MotorcycleDb : RoomDatabase() {
    abstract val dao: MotorcycleDao
}