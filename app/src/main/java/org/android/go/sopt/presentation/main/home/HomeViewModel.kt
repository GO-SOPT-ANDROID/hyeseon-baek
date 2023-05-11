package org.android.go.sopt.presentation.main.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.android.go.sopt.R
import org.android.go.sopt.data.model.main.HomeItem
import org.android.go.sopt.data.model.request.RequestSignInDto
import org.android.go.sopt.data.model.response.ResponseFollowerDto
import org.android.go.sopt.domain.AuthRepository
import org.android.go.sopt.domain.FollowerRepository
import retrofit2.Response
import javax.inject.Inject

@dagger.hilt.android.lifecycle.HiltViewModel
class HomeViewModel @Inject constructor(private val followerRepository: FollowerRepository): ViewModel(){

    fun getFollowerList(): LiveData<List<ResponseFollowerDto>> {
        val followerListLiveData = MutableLiveData<List<ResponseFollowerDto>>()
        viewModelScope.launch {
            val response = followerRepository.getUserList()
            if (response.isSuccessful) {
                followerListLiveData.value = listOf(response.body()!!)
                Log.e("getlist", "success")
            } else {
                Log.e("getlist", "fail")
            }
        }
        return followerListLiveData
    }
}