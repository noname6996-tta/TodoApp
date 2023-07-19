package com.tta.todoapplication.presentation.fragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class SharedViewModel(application: Application): AndroidViewModel(application) {
    fun verifyDataFromUser(title: String, description: String): Boolean {
        return !(title.isEmpty() || description.isEmpty())
    }
}