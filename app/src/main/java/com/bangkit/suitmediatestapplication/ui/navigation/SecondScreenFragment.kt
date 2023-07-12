package com.bangkit.suitmediatestapplication.ui.navigation

import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bangkit.suitmediatestapplication.R
import com.bangkit.suitmediatestapplication.data.model.MainViewModel
import com.bangkit.suitmediatestapplication.databinding.FragmentFirstScreenBinding
import com.bangkit.suitmediatestapplication.databinding.FragmentSecondScreenBinding

class SecondScreenFragment : Fragment() {

    private var _binding: FragmentSecondScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainViewModel: MainViewModel

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
        _binding = FragmentSecondScreenBinding.inflate(layoutInflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        mainViewModel.selectedUsername.observe(viewLifecycleOwner) { username ->
            binding.selectName.text = username
        }

        with(binding){

            val name = arguments?.getString("name")
            if (name != null) {
                inputName.text = name
            }
            val secondName = arguments?.getString("Name_From_Third")
            if (secondName != null){
                selectName.text = secondName
            }
            btnChoose.setOnClickListener {
                findNavController().navigate(R.id.action_secondScreenFragment_to_thirdScreenFragment)
            }
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

}