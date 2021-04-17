package com.example.mad03_fragments_and_navigation.quizFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mad03_fragments_and_navigation.models.Question
import com.example.mad03_fragments_and_navigation.models.QuestionCatalogue

class QuizViewModel : ViewModel() {

    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish

    private val _questions = MutableLiveData<MutableList<Question>>()
    val questions: LiveData<MutableList<Question>>
        get() = _questions

    private val _questionsCount = MutableLiveData<Int>()
    val questionsCount: LiveData<Int>
        get() = _questionsCount

    private val _question = MutableLiveData<Question>()
    val question: LiveData<Question>
        get() = _question

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private val _index = MutableLiveData<Int>()
    val index: LiveData<Int>
        get() = _index


    init {
        _score.value = 0
        _index.value = 0
        _question.value = index.value?.let { questions.value?.get(it) }
        resetQuestions()
        _questionsCount.value = _questions.value?.size
        nextQuestion()
        Log.i("QuizViewModel", "QuizViewModel created!")
    }

    private fun resetQuestions() {
        _questions.value = QuestionCatalogue().defaultQuestions.toMutableList()
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("QuizViewModel", "QuizViewModel destroyed!")
    }

    fun nextQuestion() {
        if (_questions.value?.isEmpty() == true) {
            onGameFinish()
        } else {
            _question.value = (_questions.value)?.removeAt(0)
            _index.value = (index.value)?.plus(1)
        }
    }

    fun onCorrect() {
        _score.value = (score.value)?.plus(1)
    }

    private fun onGameFinish() {
        _eventGameFinish.value = true
    }

    fun onGameFinishComplete() {
        _eventGameFinish.value = false
    }

}