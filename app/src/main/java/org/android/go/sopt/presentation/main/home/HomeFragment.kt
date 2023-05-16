package org.android.go.sopt.presentation.main.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.android.go.sopt.data.model.AuthState
import org.android.go.sopt.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding?= null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding)
    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var homeAdapter: HomeAdapter

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
        getFollowerList()
    }
    private fun initAdapter(){
        homeAdapter = HomeAdapter()
        Log.e("success", "hi")
        binding.rvHome.adapter = homeAdapter
    }

    private fun getFollowerList(){
        viewModel.getListState.observe(viewLifecycleOwner) { getListState ->
            if(getListState == AuthState.SUCCESS){
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.followerListLiveData?.let { followerList ->
                        homeAdapter.submitList(followerList)
                        Log.e("success", followerList.toString())
                    }
                }
            }else{
                Log.e("fail", "fail")
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