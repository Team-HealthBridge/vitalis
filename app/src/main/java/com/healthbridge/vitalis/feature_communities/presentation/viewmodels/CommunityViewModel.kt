package com.healthbridge.vitalis.feature_communities.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.healthbridge.vitalis.feature_communities.data.models.Community
import com.healthbridge.vitalis.feature_communities.data.models.Post
import com.healthbridge.vitalis.feature_communities.data.repository.CommunityRepository


class CommunityViewModel constructor(communityRepository: CommunityRepository) : ViewModel() {

    val communitiesResponse: MutableState<List<Community>> = communityRepository.allComunities
    val postResponse: MutableState<List<Post>> = communityRepository.post


    init {
        communityRepository.getCommunities()
        communityRepository.getPost()
    }

}

class CommunityViewModelFactory(private val repository: CommunityRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommunityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CommunityViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

