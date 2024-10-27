package io.xor.project.blogapi.repository.interfaces;


import io.xor.project.blogapi.entity.Post;

import java.util.List;

public interface IDAOPost {
    // Create !
    Post createPost(String title, String content, String description);
    Post createPost(Post post );
    List<Post> createPosts(List<Post> posts);

    // Get
    List<Post> getPosts();
    List<Post> getPosts(int page, int size);

    List<Post> getPostsByTitle(String title);
    Post getPostByTitle(String title);
    Post getPostById(Long id);

    // Put
    Post updatePost(Post post, String uuid );

    // Delete
    void deletePost(Post post);
}
