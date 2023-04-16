package com.healthbridge.vitalis.feature_communities.data.models


data class Community(
    val name: String = "",
    val bio: String = "",
    val profilePicture : String? = null,
    var members: List<Member> = emptyList(),
    var posts : List<Post> = emptyList()
) {
    constructor() : this("", "", null, emptyList(), emptyList())
}

data class Post(
    val id: String = "",
    val title: String? = "",
    var body: String? = "",
    val postImage: String? = null,
    var author: Member = Member(),
    var comments: List<Comment> = emptyList(),
    val likes: Int = 0
) {
    constructor() : this("", "", "", "", Member(), emptyList(), 0)
}

data class Member(
    var id : String = "",
    val name: String = "",
    val bio: String = "",
    val profilePicture : String? = null,
    var communities: List<Community> = emptyList()
) {
    constructor() : this("", "", "",null, emptyList())
}

data class Comment(
    var body: String = "",
    var author: Member = Member(),
    val replies: List<Comment> = emptyList(),
    val likes: Int = 0
) {
    constructor() : this("", Member(), emptyList(), 0)
}

