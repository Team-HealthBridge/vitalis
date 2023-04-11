package com.healthbridge.vitalis.feature_communities.data.repository

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.google.firebase.firestore.FirebaseFirestore
import com.healthbridge.vitalis.feature_communities.data.models.Community
import com.healthbridge.vitalis.feature_communities.data.models.Member
import com.healthbridge.vitalis.feature_communities.data.models.Post

class CommunityRepository {

    private val firestoreInstance: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    private val communitiesDocumentRef = firestoreInstance.collection("communities")

    val allComunities: MutableState<List<Community>> = mutableStateOf(emptyList())
    val post : MutableState<List<Post>> = mutableStateOf(emptyList())

    val member1 = Member("Member 1", "Member 1 Bio", "profilePicture", listOf())
    val member2 = Member("Member 2", "Member 2 Bio", "profilePicture", listOf())


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

}