package com.example.mad03_fragments_and_navigation.quizEndFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mad03_fragments_and_navigation.R
import com.example.mad03_fragments_and_navigation.databinding.FragmentQuizEndBinding


class QuizEndFragment : Fragment() {

    private lateinit var binding: FragmentQuizEndBinding
    private lateinit var viewModel: QuizEndViewModel
    private lateinit var viewModelFactory: QuizEndViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz_end, container, false)
        viewModelFactory = QuizEndViewModelFactory(QuizEndFragmentArgs.fromBundle(requireArguments()).score)
        viewModel = ViewModelProvider(this, viewModelFactory).get(QuizEndViewModel::class.java)
        viewModel.score.observe(viewLifecycleOwner, Observer { newScore ->
            binding.userScore.text = "$newScore/3"
        })

        viewModel.eventPlayAgain.observe(viewLifecycleOwner, Observer { playAgain ->
            if (playAgain) {
                findNavController().navigate(QuizEndFragmentDirections.actionQuizEndFragmentToQuizFragment())
                viewModel.onPlayAgainComplete()
            }
        })

        binding.restartQuizButton.setOnClickListener {  viewModel.onPlayAgain()  }

        return binding.root
    }

}