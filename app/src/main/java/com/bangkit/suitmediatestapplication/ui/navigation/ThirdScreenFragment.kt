package com.bangkit.suitmediatestapplication.ui.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.suitmediatestapplication.data.model.ItemAdapter
import com.bangkit.suitmediatestapplication.data.model.MainViewModel
import com.bangkit.suitmediatestapplication.databinding.FragmentThirdScreenBinding

class ThirdScreenFragment : Fragment() {

    private var _binding: FragmentThirdScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainViewModel: MainViewModel
    private lateinit var listUserAdapter: ItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentThirdScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setupRecyclerView()
        setupSwipeRefreshLayout()

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        mainViewModel.getStory(1, 10)
        mainViewModel.userList.observe(viewLifecycleOwner) { userList ->
            userList?.let { list ->
                val pagingData = PagingData.from(list)
                listUserAdapter.submitData(lifecycle, pagingData)
                showEmptyState(list.isEmpty())
            }
        }
    }

    private fun setupRecyclerView() {
        listUserAdapter = ItemAdapter()
        binding.rvUser.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = listUserAdapter
        }
    }

    private fun setupSwipeRefreshLayout() {
        binding.swipeRefresh.setOnRefreshListener {
            listUserAdapter.refresh()
        }
    }

    private fun showEmptyState(show: Boolean) {
        if (show) {
            binding.rvUser.visibility = View.GONE
            binding.empty?.visibility = View.VISIBLE
        } else {
            binding.rvUser.visibility = View.VISIBLE
            binding.empty?.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
