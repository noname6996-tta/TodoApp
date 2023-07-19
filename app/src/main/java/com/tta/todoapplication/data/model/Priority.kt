package com.tta.todoapplication.data.model

import android.os.Parcelable
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
class Priority (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var numberPriorty : Int
): Parcelable