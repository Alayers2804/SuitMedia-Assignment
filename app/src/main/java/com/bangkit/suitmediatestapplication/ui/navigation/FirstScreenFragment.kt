package com.bangkit.suitmediatestapplication.ui.navigation

import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.bangkit.suitmediatestapplication.R
import com.bangkit.suitmediatestapplication.databinding.FragmentFirstScreenBinding
import java.util.Scanner


class FirstScreenFragment : Fragment() {

    private var _binding: FragmentFirstScreenBinding? = null
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val animation = TransitionInflater.from(requireContext())
            .inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstScreenBinding.inflate(layoutInflater,container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            btnNext.setOnClickListener {
                val name = etName.text.toString()
                if (name.isNotEmpty()){
                    val bundle = Bundle().apply {
                        putString("name", name)
                    }
                    findNavController().navigate(R.id.action_firstScreenFragment_to_secondScreenFragment, bundle)
                } else {
                    val bundle = Bundle().apply {
                        putString("name", "John Doe")
                    }
                    findNavController().navigate(R.id.action_firstScreenFragment_to_secondScreenFragment, bundle)
                }
            }
            btnCheck.setOnClickListener {
                val palindrome = etPalindrome.text.toString()

                if (palindrome.isNotEmpty()) {
                    if (isPalindromeString(palindrome)) {
                        Toast.makeText(requireContext(), "$palindrome is a palindrome", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireContext(), "$palindrome is not a palindrome", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(requireContext(), "Please enter a palindrome", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun isPalindromeString(inputStr: String): Boolean {
        val sb = StringBuilder(inputStr)
        val reverseStr = sb.reverse().toString()

        return inputStr.equals(reverseStr, ignoreCase = true)
    }

}