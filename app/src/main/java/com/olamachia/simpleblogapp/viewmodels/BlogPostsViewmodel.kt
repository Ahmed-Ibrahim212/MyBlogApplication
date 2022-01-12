package com.olamachia.simpleblogapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olamachia.simpleblogapp.model.domain.MainRepository
import com.olamachia.simpleblogapp.model.domain.Post
import com.olamachia.simpleblogapp.util.DataState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class BlogPostsViewModel constructor(private val mainRepository: MainRepository) : ViewModel() {


    private val _dataState: MutableLiveData<DataState<List<Post>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<Post>>>
        get() = _dataState

    init {
        fetchPosts()
    }


    fun fetchPosts() {
        viewModelScope.launch {
            mainRepository.getPosts().collect {
                _dataState.value = it
            }
        }
    }

    fun insertPost(post: Post) {
        viewModelScope.launch {
            mainRepository.insertPost(post)
        }
    }


    private var cachedPosts = MutableLiveData<DataState<List<Post>>>()
    private var isSearchingActive: Boolean = true
    var isSearching = MutableStateFlow(false)

    fun searchPosts(query: String) {

        if (isSearchingActive) {
            cachedPosts.value = _dataState.value
            isSearchingActive = false
        }

        val listToSearch = if (isSearchingActive) {
            dataState.value
        } else {
            cachedPosts.value
        }
        viewModelScope.launch {
            if (query.isEmpty()) {
                _dataState.value = cachedPosts.value
                isSearching.value = false
                isSearchingActive = true
                return@launch
            } else {
                val results = listToSearch?.data?.filter {
                    it.title.contains(query.trim(), ignoreCase = true) ||
                            it.postId.toString().contains(query.trim())
                }
                results?.let {
                    _dataState.value = DataState.success(results)
                }
            }
            if (isSearchingActive) {
                cachedPosts.value = _dataState.value
                isSearchingActive = false
            }
            isSearching.value = true
        }
    }
}