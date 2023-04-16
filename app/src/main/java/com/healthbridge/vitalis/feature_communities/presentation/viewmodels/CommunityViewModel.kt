package com.healthbridge.vitalis.feature_communities.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.healthbridge.vitalis.feature_communities.data.models.Comment
import com.healthbridge.vitalis.feature_communities.data.models.Community
import com.healthbridge.vitalis.feature_communities.data.models.Member
import com.healthbridge.vitalis.feature_communities.data.models.Post
import com.healthbridge.vitalis.feature_communities.data.repository.CommunityRepository
import kotlinx.coroutines.launch


class CommunityViewModel constructor(communityRepository: CommunityRepository) : ViewModel() {

    val communitiesResponse: MutableState<List<Community>> = communityRepository.allComunities
    val postResponse: MutableState<List<Post>> = communityRepository.post
    val isMember : MutableState<Boolean> = mutableStateOf(false)
    val postComments : MutableState<List<Comment>> = mutableStateOf(emptyList())
    val post : MutableState<Post> = mutableStateOf(Post("","","", "", Member("", "", "", ""),  emptyList()))


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

    fun addComment(communityName: String, post: Post, comment: Comment){
        viewModelScope.launch {
            val communityRepository = CommunityRepository()
            communityRepository.addComment(communityName, post, comment)
        }
    }

    fun getComments(communityName: String, post: Post){
        viewModelScope.launch {
            val communityRepository = CommunityRepository()
            postComments.value = communityRepository.getPostComments(communityName, post)
        }
    }

    fun getPostById(communityName: String, postBody: String){
        viewModelScope.launch {
            val communityRepository = CommunityRepository()
            post.value = communityRepository.getPostByBody(communityName, postBody)!!
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

