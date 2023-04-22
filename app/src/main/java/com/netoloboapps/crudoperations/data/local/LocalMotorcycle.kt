package com.netoloboapps.crudoperations.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocalMotorcycle(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "brand_name")
    val brandName: String,
    @ColumnInfo(name = "model")
    val model: String,
)

class InvalidMotorcycleException(message: String) : Exception(message)
