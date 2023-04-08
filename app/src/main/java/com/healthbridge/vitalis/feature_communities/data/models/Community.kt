package com.healthbridge.vitalis.feature_communities.data.models

import org.w3c.dom.Comment

data class Community(
    val name: String = "",
    val bio: String = "",
    val profilePicture : String? = null,
    val members: List<Member> = emptyList(),
    val posts : List<Post> = emptyList()
) {
    constructor() : this("", "", null, emptyList(), emptyList())
}

data class Post(
    val title: String? = "",
    val body: String? = "",
    val postImage: String? = null,
    val author: Member = Member(),
    val comments: List<Comment> = emptyList()
) {
    constructor() : this("", "", null, Member(), emptyList())
}

data class Member(
    val name: String = "",
    val bio: String = "",
    val profilePicture : String? = null,
    val communities: List<Community> = emptyList()
) {
    constructor() : this("", "", null, emptyList())
}

