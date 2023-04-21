package org.android.go.sopt.presentation.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import org.android.go.sopt.databinding.FragmentHomeBinding
import org.android.go.sopt.presentation.main.home.HomeAdapter
import org.android.go.sopt.util.extension.scrollToTop

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding?= null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding)
    private val viewmodel by viewModels<HomeViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvHome.scrollToTop()
        initAdapter(HomeAdapter(requireContext()))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initAdapter(adapter: HomeAdapter){
        adapter.submitList(viewmodel.fakeRepoList)
        binding.rvHome.adapter = adapter
    }
}