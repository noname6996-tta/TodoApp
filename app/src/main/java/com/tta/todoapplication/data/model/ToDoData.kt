package com.tta.todoapplication.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "todo_table")
@Parcelize
data class ToDoData(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var desc: String,
    var numberPriorty : Int,
    var time : String,
    var numberColor : Int,
    var numberIcon : Int,
    var nameCategory : String
): Parcelable