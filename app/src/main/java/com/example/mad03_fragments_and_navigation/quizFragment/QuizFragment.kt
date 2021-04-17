package com.example.mad03_fragments_and_navigation.quizFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.mad03_fragments_and_navigation.R
import com.example.mad03_fragments_and_navigation.databinding.FragmentQuizBinding


class QuizFragment : Fragment() {

    private lateinit var binding: FragmentQuizBinding
    private lateinit var viewModel: QuizViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz, container, false)
        Log.i("QuizFragment", "Called ViewModelProvider.get")
        viewModel = ViewModelProvider(this).get(QuizViewModel::class.java)

        viewModel.questionsCount.observe(viewLifecycleOwner, { counter ->
            binding.questionsCount = counter
        })

        viewModel.question.observe(viewLifecycleOwner, Observer { newQuestion ->
            binding.question = newQuestion
        })

        viewModel.index.observe(viewLifecycleOwner, { newIndex ->
            binding.index = newIndex
        })

        viewModel.eventGameFinish.observe(viewLifecycleOwner, Observer<Boolean> { hasFinished ->
            if (hasFinished) gameFinished()
        })

        binding.btnNext.setOnClickListener {
            if (getSelectedRadioButton() != -1) {
                if (binding.question?.answers?.get(getSelectedRadioButton())?.isCorrectAnswer == true) {
                    Log.i("QuizFragment", "This Question was correct")
                    onCorrect()
                }
                nextQuestion()
            }
        }


        return binding.root
    }

    private fun nextQuestion() {
        viewModel.nextQuestion()
    }

    private fun onCorrect() {
        viewModel.onCorrect()
    }

    private fun gameFinished() {
        val action = QuizFragmentDirections.actionQuizFragmentToQuizEndFragment()
        action.score = viewModel.score.value ?: 0
        NavHostFragment.findNavController(this).navigate(action)
        viewModel.onGameFinishComplete()
    }

    private fun getSelectedRadioButton(): Int {
        val selectedRadioButtonId = binding.answerBox.checkedRadioButtonId
        return if (selectedRadioButtonId == -1) {
            Toast.makeText(requireContext(), "Please select an answer", Toast.LENGTH_SHORT).show()
            selectedRadioButtonId
        } else {
            val radioButton: View = binding.answerBox.findViewById(selectedRadioButtonId)
            binding.answerBox.indexOfChild(radioButton)
        }
    }

}