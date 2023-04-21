package org.android.go.sopt.presentation.main.gallery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.android.go.sopt.databinding.FragmentGalleryBinding


class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding?= null
    private val binding: FragmentGalleryBinding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGalleryBinding.inflate(inflater,container,false)
        return binding.root
        // View를 생성하는 로직
        // Inflate the layout for this fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

    }
}