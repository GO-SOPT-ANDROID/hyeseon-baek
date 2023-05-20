package org.android.go.sopt.presentation.main.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.android.go.sopt.R
import org.android.go.sopt.data.model.AuthState
import org.android.go.sopt.data.model.main.Follower
import org.android.go.sopt.data.model.main.HomeItem
import org.android.go.sopt.data.model.request.RequestSignInDto
import org.android.go.sopt.data.model.response.ResponseFollowerDto
import org.android.go.sopt.domain.AuthRepository
import org.android.go.sopt.domain.FollowerRepository
import retrofit2.Response
import javax.inject.Inject

@dagger.hilt.android.lifecycle.HiltViewModel
class HomeViewModel @Inject constructor(private val followerRepository: FollowerRepository) :
    ViewModel() {
    private val _followerListLiveData = MutableLiveData<List<Follower>>()
    val followerListLiveData: List<Follower>?
        get() = _followerListLiveData.value
    private val _getListState = MutableLiveData<AuthState>()
    val getListState: LiveData<AuthState>
        get() = _getListState

    init {
        getFollowerList()
    }

    fun getFollowerList() {
        viewModelScope.launch {
            followerRepository.getUserList()
                .onSuccess { response ->
                    _followerListLiveData.value = response
                    _getListState.value = AuthState.SUCCESS
                }
                .onFailure {
                    _getListState.value = AuthState.FAIL
                }
        }
    }
}