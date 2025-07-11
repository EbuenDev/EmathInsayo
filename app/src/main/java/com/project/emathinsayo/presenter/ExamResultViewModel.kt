package com.project.emathinsayo.presenter

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.emathinsayo.common.Resource
import com.project.emathinsayo.data.BookRepository
import com.project.emathinsayo.data.UserProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExamResultViewModel @Inject constructor(
    private val bookRepository: BookRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _level = MutableStateFlow<String?>(savedStateHandle["level"])
    val level = _level.asStateFlow()

    private val _score = MutableStateFlow<Resource<Int?>>(Resource.Loading())
    val score = _score.asStateFlow()

    private val _profile = MutableStateFlow<Resource<UserProfile?>>(Resource.Loading())
    val profile = _profile.asStateFlow()

    init {
        observeLevel()
    }

    private fun observeLevel() {
        viewModelScope.launch {
            level.filterNotNull().distinctUntilChanged().collect { levelValue ->
                loadScore(levelValue)
                loadUserProfile()
            }
        }
    }

    private suspend fun loadScore(level: String) {
        _score.value = Resource.Success(bookRepository.getScore(level))
    }

    private suspend fun loadUserProfile() {
        _profile.value = Resource.Success(bookRepository.getUserProfile())
    }
}
