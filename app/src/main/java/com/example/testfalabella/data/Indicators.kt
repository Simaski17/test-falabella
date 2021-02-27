package com.example.testfalabella.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Indicators (
    @PrimaryKey(autoGenerate = false)
    val codigo: String,
    val nombre: String,
    @ColumnInfo(name = "unidad_medida")
    val unidadMedida: String,
    val fecha: String,
    val valor: String
)