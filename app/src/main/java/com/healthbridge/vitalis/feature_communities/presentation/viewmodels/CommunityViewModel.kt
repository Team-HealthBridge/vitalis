package com.healthbridge.vitalis.feature_communities.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.healthbridge.vitalis.feature_communities.data.models.Community
import com.healthbridge.vitalis.feature_communities.data.models.Member
import com.healthbridge.vitalis.feature_communities.data.models.Post
import com.healthbridge.vitalis.feature_communities.data.repository.CommunityRepository
import kotlinx.coroutines.launch


class CommunityViewModel constructor(communityRepository: CommunityRepository) : ViewModel() {

    val communitiesResponse: MutableState<List<Community>> = communityRepository.allComunities
    val postResponse: MutableState<List<Post>> = communityRepository.post
    val isMember : MutableState<Boolean> = mutableStateOf(false)


    init {
        communityRepository.getCommunities()
        communityRepository.getPost("Sleep")
    }

    fun getPost(string: String){
        val communityRepository = CommunityRepository()
        communityRepository.getPost(string)
    }

    fun followCommunity(communityName: String, member: Member){
        val communityRepository = CommunityRepository()
        communityRepository.followCommunity(communityName, member)
    }

    fun isMember(member: Member, communityName: String){
        viewModelScope.launch {
            val communityRepository = CommunityRepository()
            isMember.value = communityRepository.isCommunityMember(communityName, member)
        }
    }

    fun addPost(communityName: String, post: Post){
        viewModelScope.launch {
            val communityRepository = CommunityRepository()
            communityRepository.addPost(communityName, post)
        }
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

