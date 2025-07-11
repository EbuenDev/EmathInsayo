package com.project.emathinsayo.presenter

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.emathinsayo.common.Resource
import com.project.emathinsayo.data.Book
import com.project.emathinsayo.data.BookRepository
import com.project.emathinsayo.data.UserProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.String
import kotlin.collections.Map

@HiltViewModel
class BookAppViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val bookRepository: BookRepository
): ViewModel() {
    private val _lessons = MutableStateFlow<List<Book>>(emptyList())
    val lesson = _lessons.asStateFlow()

    private val _profile = MutableStateFlow<Resource<UserProfile?>>(Resource.Success(savedStateHandle["USER"]))
    val profile = _profile.asStateFlow()

    private val _scores = MutableStateFlow<Map<String, Int?>?>(null)
    val score = _scores.asStateFlow()

    val favorites: StateFlow<List<String>> = bookRepository.getFavoriteTitles()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val listCopy = MutableStateFlow<List<Book>>(emptyList())

    private val _page = MutableStateFlow(1)
    val page = _page.asStateFlow()

    private var job: Job? = null

    fun updateUserProfile(name: String,selectedAvatar: Int) {
        viewModelScope.launch {
            bookRepository.updateUserProfile(name, selectedAvatar)
            _profile.value = Resource.Success(bookRepository.getUserProfile())
        }
    }

    fun loadAllScores() {
        viewModelScope.launch {
            _scores.value = bookRepository.getAllScore()
        }
    }


}