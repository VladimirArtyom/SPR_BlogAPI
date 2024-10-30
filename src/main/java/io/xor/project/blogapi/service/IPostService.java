package io.xor.project.blogapi.service;

import io.xor.project.blogapi.entity.Post;
import io.xor.project.blogapi.payload.PostDTO;

import java.util.List;

public interface IPostService {

    PostDTO createPost(PostDTO post);
    List<PostDTO> createPosts(List<PostDTO> posts);

    PostDTO updatePost(PostDTO new_post, String title);

    PostDTO getPost(Long id);
    List<PostDTO> getPosts();
    List<PostDTO> getPosts(int page, int size);
    void deletePost(PostDTO post);

    PostDTO getPostByTitle(String title);
    PostDTO getPostById(Long id);
    List<PostDTO> getPostsByTitle(String title);

}
