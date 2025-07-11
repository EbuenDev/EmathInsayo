package com.project.emathinsayo.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.emathinsayo.common.Resource
import com.project.emathinsayo.data.BookRepository
import com.project.emathinsayo.data.UserProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val bookRepository: BookRepository): ViewModel() {
    private val _profile = MutableStateFlow<Resource<UserProfile?>>(Resource.Loading())
    val profile = _profile.asStateFlow()

    init {
        getUserProfile()
    }

    fun updateUserProfile(name: String,selectedAvatar: Int) {
        viewModelScope.launch {
            bookRepository.updateUserProfile(name, selectedAvatar)
            _profile.value = Resource.Success(bookRepository.getUserProfile())
        }
    }

    private fun getUserProfile() {
        viewModelScope.launch {
            _profile.value = Resource.Success(bookRepository.getUserProfile())
        }
    }
}

