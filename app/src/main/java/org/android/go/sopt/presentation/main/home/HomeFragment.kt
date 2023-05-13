package org.android.go.sopt.presentation.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.android.go.sopt.databinding.FragmentHomeBinding

@AndroidEntryPoint
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
        initAdapter()
    }
    private fun initAdapter(){
        val adapter = HomeAdapter()
        binding.rvHome.adapter = adapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewmodel.followerListLiveData?.let { followerList ->
                adapter.submitList(followerList)
            }
        }
    }

    fun scrollToTop(){
        val binding = _binding ?: return
        binding.rvHome.smoothScrollToPosition(0)
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}