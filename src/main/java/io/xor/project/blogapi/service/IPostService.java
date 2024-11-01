package io.xor.project.blogapi.service;

import io.xor.project.blogapi.entity.Post;
import io.xor.project.blogapi.payload.PostDTO;

import java.util.List;
import java.util.Optional;

public interface IPostService {

    PostDTO createPost(PostDTO post);
    List<PostDTO> createPosts(List<PostDTO> posts);

    PostDTO updatePost(PostDTO new_post, String title);

    PostDTO getPost(Long id);
    List<PostDTO> getPosts();
    List<PostDTO> getPosts(int page, int size);
    void deletePost(PostDTO post);

    Optional<PostDTO> getPostByTitle(String title);
    Optional<PostDTO> getPostById(Long id);
    Optional<List<PostDTO>> getPostsByTitle(String title);

    void dev_exception();
}
