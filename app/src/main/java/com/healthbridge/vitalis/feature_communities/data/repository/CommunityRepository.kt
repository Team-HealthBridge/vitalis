package com.healthbridge.vitalis.feature_communities.data.repository

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.google.firebase.firestore.FirebaseFirestore
import com.healthbridge.vitalis.feature_communities.data.models.Community
import com.healthbridge.vitalis.feature_communities.data.models.Member
import com.healthbridge.vitalis.feature_communities.data.models.Post
import kotlinx.coroutines.tasks.await

class CommunityRepository {

    private val firestoreInstance: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    private val communitiesDocumentRef = firestoreInstance.collection("communities")

    val allComunities: MutableState<List<Community>> = mutableStateOf(emptyList())
    val post : MutableState<List<Post>> = mutableStateOf(emptyList())


    val member1 = Member("1","Member 1", "Member 1 Bio", "profilePicture", listOf())
    val member2 = Member("2","Member 2", "Member 2 Bio", "profilePicture", listOf())


    val community = Community("Sleep", "Sleep is the only way the body heals", "profilePicture", listOf(member1, member2))
    val community2 = Community("Runners", "Remember to stop and touch the flowers", "profilePicture", listOf(member1, member2))

    fun initCommunities() {
        communitiesDocumentRef.document("Sleep").set(community)
        communitiesDocumentRef.document("Runners").set(community2)
    }


    fun getCommunities( ) {
        communitiesDocumentRef.get().addOnSuccessListener { querySnapshot ->
            if (!querySnapshot.isEmpty) {
                for (document in querySnapshot.documents) {
                    val community = document.toObject(Community::class.java)
                    if (community != null) {
                        allComunities.value = allComunities.value + community
                    }
                }
            }
        }
    }

    fun getPost(string: String){
        communitiesDocumentRef.document(string).get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                val community = documentSnapshot.toObject(Community::class.java)
                if (community != null) {
                    post.value = community.posts
                }
            }
        }
    }

    fun followCommunity(communityName: String, member: Member){
        communitiesDocumentRef.document(communityName).get().addOnSuccessListener() { documentSnapshot ->
            if (documentSnapshot.exists()) {
                val community = documentSnapshot.toObject(Community::class.java)
                if (community != null) {
                    var members = community.members
                    if (members.contains(member)){
                        members.minus(member)
                        communitiesDocumentRef.document(community.name).update("members", members)
                        member.communities.minus(community)
                    } else {
                        members = members.plus(member)
                        communitiesDocumentRef.document(community.name).update("members", members)
                        member.communities.plus(community)
                    }
                }
            }
        }
    }

    suspend fun isCommunityMember(communityName: String, member: Member): Boolean{

        val documentSnapshot = communitiesDocumentRef.document(communityName).get().await()

        if (documentSnapshot != null) {
            if (documentSnapshot.exists()) {
                val community = documentSnapshot.toObject(Community::class.java)
                if (community != null) {
                    val members = community.members
                    for (eachMember in members){
                        if (eachMember.id == member.id){
                            return true
                        }
                    }
                }
            }
        }

        return false
    }

    suspend fun addPost(communityName: String, post: Post){
        val documentSnapshot = communitiesDocumentRef.document(communityName).get().await()

        if (documentSnapshot != null) {
            if (documentSnapshot.exists()) {
                val community = documentSnapshot.toObject(Community::class.java)
                if (community != null) {
                    var posts = community.posts
                    posts = posts.plus(post)
                    communitiesDocumentRef.document(community.name).update("posts", posts)
                }
            }
        }
    }

}