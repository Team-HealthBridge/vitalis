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
    val title: String? = "",
    var body: String? = "",
    val postImage: String? = null,
    var author: Member = Member(),
    val comments: List<Comment> = emptyList(),
    val likes: Int = 0
) {
    constructor() : this("", "", null, Member(), emptyList(), 0)
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
    val body: String = "",
    val author: Member = Member(),
    val replies: List<Comment> = emptyList(),
    val likes: Int = 0
) {
    constructor() : this("", Member(), emptyList(), 0)
}

