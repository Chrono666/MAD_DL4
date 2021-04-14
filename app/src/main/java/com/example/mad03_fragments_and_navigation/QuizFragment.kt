package com.example.mad03_fragments_and_navigation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.mad03_fragments_and_navigation.databinding.FragmentQuizBinding
import com.example.mad03_fragments_and_navigation.models.QuestionCatalogue
import com.example.mad03_fragments_and_navigation.utils.QuizViewModel
import kotlin.math.absoluteValue
import kotlin.reflect.typeOf


class QuizFragment : Fragment() {

    private lateinit var binding: FragmentQuizBinding
    private val questions =
        QuestionCatalogue().defaultQuestions    // get a list of questions for the game
    private var score = 0          // save the user's score
    private var index = 0         // index for question data to show

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz, container, false)

        binding.index = index
        binding.questionsCount = questions.size
        binding.question = questions[index]

        binding.btnNext.setOnClickListener {
            nextQuestion()
        }

        return binding.root
    }

    private fun nextQuestion() {
        calculateScore(getAnswerIndex())
        if (index < 2) {
            binding.question = questions[++index]
        } else {
            findNavController().navigate(
                QuizFragmentDirections.actionQuizFragmentToQuizEndFragment(
                    score
                )
            )
        }
    }

    private fun calculateScore(index: Int) {
        if (index != -1) {
            if (binding.question?.answers?.get(index)?.isCorrectAnswer == true) {
                score += 1
            }
        }
    }

    private fun getAnswerIndex(): Int {
        val selectedRadioButtonId = binding.answerBox.checkedRadioButtonId
        return if (selectedRadioButtonId == -1) {
            displayToast("Please select an answer")
            selectedRadioButtonId
        } else {
            val radioButton: View = binding.answerBox.findViewById(selectedRadioButtonId)
            binding.answerBox.indexOfChild(radioButton)
        }
    }


    private fun displayToast(txt: String) {
        Toast.makeText(requireContext(), txt, Toast.LENGTH_SHORT).show()
    }

}