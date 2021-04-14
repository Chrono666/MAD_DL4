package com.example.mad03_fragments_and_navigation.utils

import android.util.Log
import androidx.lifecycle.ViewModel

class QuizViewModel: ViewModel() {



    override fun onCleared() {
        super.onCleared()
        Log.i("QuizViewModel", "QuizViewModel destroyed!")
    }
}