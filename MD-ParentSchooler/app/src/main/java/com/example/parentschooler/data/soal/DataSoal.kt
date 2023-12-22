package com.example.parentschooler.data.soal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DataSoal(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "Date")
    var date : String ?= null,

    @ColumnInfo(name = "Type")
    var type : String ?= null,

    @ColumnInfo(name = "Soal1")
    var soal1 : Int ?= null,

    @ColumnInfo(name = "Soal2")
    var soal2 : Int ?= null,

    @ColumnInfo(name = "Soal3")
    var soal3 : Int ?= null,

    @ColumnInfo(name = "Soal4")
    var soal4 : Int ?= null,

    @ColumnInfo(name = "Soal5")
    var soal5 : Int ?= null,

    @ColumnInfo(name = "Soal6")
    var soal6 : Int ?= null,

    @ColumnInfo(name = "Soal7")
    var soal7 : Int ?= null,

    @ColumnInfo(name = "Soal8")
    var soal8 : Int ?= null,

    @ColumnInfo(name = "Soal9")
    var soal9 : Int ?= null,

    @ColumnInfo(name = "Soal10")
    var soal10 : Int ?= null,

    @ColumnInfo(name = "Soal11")
    var soal11 : Int ?= null,

    @ColumnInfo(name = "Soal12")
    var soal12 : Int ?= null,

    @ColumnInfo(name = "Soal13")
    var soal13 : Int ?= null,

    @ColumnInfo(name = "Soal14")
    var soal14 : Int ?= null,
)
